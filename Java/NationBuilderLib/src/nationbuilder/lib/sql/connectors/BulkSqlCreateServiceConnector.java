package nationbuilder.lib.sql.connectors;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.MappingInfo;
import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RelationResolveService;
import nationbuilder.lib.Ruby.RelationScanService;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;
import nationbuilder.lib.Ruby.configuration.RubyConfiguration;
import nationbuilder.lib.Ruby.orm.RubyObjectKey;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.data.map.ResponseData;
import nationbuilder.lib.sql.SqlQueryManager;
import nationbuilder.lib.sql.SqlResponseData;

/**
 * Created by patrick on 12/16/14.
 */
public class BulkSqlCreateServiceConnector implements RubyCreateService
{
	SqlQueryManager sqlQueryManager;
	SqlObjectBuilder objectBuilder;
	HashMap<RubyObjectKey,String> persistedObjects;

	public BulkSqlCreateServiceConnector(SqlObjectBuilder objectBuilder,SqlQueryManager queryManager)
	{
        this.sqlQueryManager = queryManager;
		//this.sqlQueryManager = new SqlQueryManager(RubyConfiguration.mySqlUsername,RubyConfiguration.mySqlPassword,RubyConfiguration.mySqlServer,RubyConfiguration.mySqlDatabase,RubyConfiguration.mySqlTempDir);
		this.objectBuilder = objectBuilder;
		this.persistedObjects = new HashMap<>();

	}

	@Override
	public ResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}
	// TODO: RubyModel als parameter toevoegen, nu is alles Object dit kan zorgen voor bugs
	@Override
	public ResponseData postObject(Object objectToPost, String resourceUrl) throws ObjectPersistanceFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException {
        SqlResponseData responseData = new SqlResponseData();


            String sql = this.objectBuilder.createStringFromObject(objectToPost);

            RubyObjectKey  rubyObjectKey  =  new RubyObjectKey(resourceUrl,(RubyModel)objectToPost);
            this.persistedObjects.put(rubyObjectKey, sql);
            responseData.setSql(sql);


		return responseData;
	}

    public String resolveUnresolvedFields(RubyModel key,String value)
    {
        String resolvedSql = "";

        Pattern p = Pattern.compile(".bui.([_a-z-A-Z-0-9]*).eui");
        Matcher m  = p.matcher(value);
      //  if(m.matches()) {
            while (m.find()) {

                String field_id = m.group(1);
                String stripped_field_id = field_id.replace("_id","");
                try {
                    // expecting this to be ReferenceMapping if not well we get a cast exception
                      Field refMappingField = key.getClass().getDeclaredField(stripped_field_id); //.get(key);
                      refMappingField.setAccessible(true);
                      Object refMapping = refMappingField.get(key);
                      if(refMapping != null)
                      {
                          ReferenceMapping rm =  (ReferenceMapping)refMapping;
                          resolvedSql = value.replace("<bui>" + field_id +"<eui>",rm.getID().getId());
                      }
                      else
                      {
                          resolvedSql = value.replace("<bui>" + field_id + "<eui>","0");
                      }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
       // }
        return resolvedSql;

    }
    private boolean hasUnresolvedfields(String sqlString)
    {
        boolean result = false;

           // TODO: char sequence in een variable zetten
           if(sqlString.contains("<bui>")) {
               result = true;
           }
        return result;
    }

    @Override
    public void commit() throws RubyException {

        Iterator poit = this.persistedObjects.entrySet().iterator();
        HashMap<String,List<String>> rows = new HashMap<>();
        RelationResolveService relationResolveService = RubyDataServiceAccessor.getInstance().getService(RelationResolveService.class);
        RelationScanService relationScanService = RubyDataServiceAccessor.getInstance().getService(RelationScanService.class);
        relationResolveService.resolveForeignKeys(relationScanService.scanForRelations(
         this.persistedObjects.entrySet().iterator()));

        while(poit.hasNext())
        {
            Map.Entry pair = (Map.Entry)poit.next();
            RubyObjectKey objectKey = (RubyObjectKey)pair.getKey();

            RubyModel model =  objectKey.getObject();


          //  Entity entity =  model.getClass().getAnnotation(Entity.class);
            String sqlString = (String)pair.getValue();
            if(hasUnresolvedfields(sqlString))
            {
              sqlString =  resolveUnresolvedFields(model,sqlString);
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
