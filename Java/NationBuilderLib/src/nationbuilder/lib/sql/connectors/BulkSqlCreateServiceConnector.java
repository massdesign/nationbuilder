package nationbuilder.lib.sql.connectors;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.ModelPayload;
import nationbuilder.lib.Ruby.services.RelationResolveService;
import nationbuilder.lib.Ruby.RelationScanService;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;
import nationbuilder.lib.Ruby.configuration.RubyConfiguration;
import nationbuilder.lib.Ruby.orm.RubyObjectKey;
import nationbuilder.lib.Ruby.services.ResolveUnresolvedFieldsService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.data.map.ResponseData;
import nationbuilder.lib.sql.QueryManager;
import nationbuilder.lib.sql.SqlQueryManager;
import nationbuilder.lib.sql.SqlResponseData;

/**
 * @author patrick.ekkel
 */
public class BulkSqlCreateServiceConnector implements RubyCreateService
{
	QueryManager sqlQueryManager;
	SqlObjectBuilder objectBuilder;
	HashMap<RubyObjectKey,String> persistedObjects;

	public BulkSqlCreateServiceConnector(SqlObjectBuilder objectBuilder,QueryManager queryManager)
	{
        this.sqlQueryManager = queryManager;
		this.objectBuilder = objectBuilder;
		this.persistedObjects = new HashMap<>();

	}

	@Override
	public ResponseData postObject(ModelPayload modelPayload, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}

	@Override
	public ResponseData postObject(ModelPayload modelPayload, String resourceUrl) throws ObjectPersistanceFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException {
        SqlResponseData responseData = new SqlResponseData();

        RubyModel objectToPost = modelPayload.getRubyModel();
        ClassMap clazzMap = modelPayload.getClassMap();

        // TODO: bepalen wat we doen Strategy per class en Single instance onderbrengen in een soort van statemachine
        String sql;
        RubyObjectKey rubyObjectKey;

        if(modelPayload.getInhiritanceStrategy() == InhiritanceStrategy.TablePerClass) {
            sql = this.objectBuilder.createStringFromObject(clazzMap.getCurrent(), objectToPost);
        }
        else {
            sql = this.objectBuilder.createStringFromObject(objectToPost.getClass(),objectToPost);

        }
        rubyObjectKey = new RubyObjectKey(resourceUrl, objectToPost,clazzMap.getCurrent());
        this.persistedObjects.put(rubyObjectKey, sql);
        responseData.setSql(sql);

        return responseData;
	}

    @Override
    public void commit() throws RubyException {

        Iterator poit = this.persistedObjects.entrySet().iterator();
        HashMap<String,List<String>> rows = new HashMap<>();
        RelationResolveService relationResolveService = RubyDataServiceAccessor.getInstance().getService(RelationResolveService.class);
        RelationScanService relationScanService = RubyDataServiceAccessor.getInstance().getService(RelationScanService.class);
        ResolveUnresolvedFieldsService resolveUnresolvedFieldsService = RubyDataServiceAccessor.getInstance().getService(ResolveUnresolvedFieldsService.class);
        relationResolveService.resolveForeignKeys(relationScanService.scanForRelations(
         this.persistedObjects.entrySet().iterator()));

        while(poit.hasNext())
        {
            Map.Entry pair = (Map.Entry)poit.next();
            RubyObjectKey objectKey = (RubyObjectKey)pair.getKey();

            RubyModel model =  objectKey.getObject();


            String sqlString = (String)pair.getValue();
            if(resolveUnresolvedFieldsService.hasUnresolvedfields(sqlString))
            {
              sqlString =  resolveUnresolvedFieldsService.resolve(objectKey.getClazz(),model,sqlString);
              pair.setValue(sqlString);
            }

            if(rows.containsKey(objectKey.getTable()))
            {
                rows.get(objectKey.getTable()).add(sqlString);
            }
            else
            {
                List<String> newList = new ArrayList<>();
                newList.add(sqlString);
                rows.put(objectKey.getTable(),newList);
            }
            model.setCommitted(true);
        }


        try {
            if(rows.size() > 0) {
                Iterator rit = rows.entrySet().iterator();
                while(rit.hasNext())
                {
                    Map.Entry pair = (Map.Entry)rit.next();
                    List<String> tablerows = (List<String>)pair.getValue();
                    this.sqlQueryManager.executeBulkInsert(tablerows,(String)pair.getKey());
                }

            }
        } catch (SQLException e) {
            throw new BulkInsertFailedException(e.getMessage());
        }

    }
}
