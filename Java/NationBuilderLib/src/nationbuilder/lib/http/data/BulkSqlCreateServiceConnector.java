package nationbuilder.lib.http.data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyConfiguration;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.sql.ObjectMap;
import nationbuilder.lib.sql.SqlObjectToRowConverter;

/**
 * Created by patrick on 12/16/14.
 */
public class BulkSqlCreateServiceConnector implements RubyCreateService
{
	SqlQueryManager sqlQueryManager;
	ObjectBuilder objectBuilder;
	HashMap<RubyModel,String> persistedObjects;
//	SqlObjectToRowConverter sqlObjectToRowConverter;
	public BulkSqlCreateServiceConnector(ObjectBuilder objectBuilder)
	{
		this.sqlQueryManager = new SqlQueryManager(RubyConfiguration.mySqlUsername,RubyConfiguration.mySqlPassword,RubyConfiguration.mySqlServer,RubyConfiguration.mySqlDatabase);
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
	public ResponseData postObject(Object objectToPost, String resourceUrl) throws ObjectPersistanceFailedException, ObjectConversionFailedException, MissingAnnotationException {
        SqlResponseData responseData = new SqlResponseData();
		String sql = this.objectBuilder.createStringFromObject(objectToPost);
        this.persistedObjects.put((RubyModel)objectToPost,sql);
        responseData.setSql(sql);
		return responseData;
	}

    @Override
    public void commit() throws RubyException {

        Iterator poit = this.persistedObjects.entrySet().iterator();
        HashMap<String,List<String>> rows = new HashMap<>();



        while(poit.hasNext())
        {
            Map.Entry pair = (Map.Entry)poit.next();
            RubyModel model = (RubyModel)pair.getKey();
            Entity entity =  model.getClass().getAnnotation(Entity.class);
            String sqlString = (String)pair.getValue();

            if(rows.containsKey(entity.tableName()))
            {
                rows.get(entity.tableName()).add(sqlString);
            }
            else
            {
                List<String> newList = new ArrayList<>();
                newList.add(sqlString);
                rows.put(entity.tableName(),newList);
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