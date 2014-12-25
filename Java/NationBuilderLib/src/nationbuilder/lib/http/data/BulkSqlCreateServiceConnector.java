package nationbuilder.lib.http.data;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
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
	List<ObjectMap> persistedObjects;
//	SqlObjectToRowConverter sqlObjectToRowConverter;
	public BulkSqlCreateServiceConnector(ObjectBuilder objectBuilder)
	{
		this.sqlQueryManager = new SqlQueryManager(RubyConfiguration.mySqlUsername,RubyConfiguration.mySqlPassword,RubyConfiguration.mySqlServer,RubyConfiguration.mySqlDatabase);
		this.objectBuilder = objectBuilder;
		this.persistedObjects = new ArrayList<>();
	//	this.sqlObjectToRowConverter = new SqlObjectToRowConverter();
	}

	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}
	// TODO: RubyModel als parameter toevoegen, nu is alles Object dit kan zorgen voor bugs
	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl) throws ObjectPersistanceFailedException, ObjectConversionFailedException {
		try
		{
			String sql = this.objectBuilder.createStringFromObject(objectToPost);
			sqlQueryManager.executeBulkInsert(sql);
		}
		catch (SQLException e)
		{
			throw new ObjectPersistanceFailedException((RubyModel)objectToPost,e);
		}
		return null;
	}
}
