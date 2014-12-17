package nationbuilder.lib.http.data;

import java.io.IOException;
import java.sql.SQLException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyConfiguration;
import nationbuilder.lib.connectors.ObjectBuilder;

/**
 * Created by patrick on 12/16/14.
 */
public class BulkSqlCreateServiceConnector implements RubyCreateService
{
	SqlQueryManager sqlQueryManager;
	ObjectBuilder objectBuilder;
	public BulkSqlCreateServiceConnector(ObjectBuilder objectBuilder)
	{
		this.sqlQueryManager = new SqlQueryManager(RubyConfiguration.mySqlUsername,RubyConfiguration.mySqlPassword,RubyConfiguration.mySqlServer,RubyConfiguration.mySqlDatabase);
		this.objectBuilder = objectBuilder;
	}

	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}

	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl) throws ObjectPersistanceFailedException
	{
		String sql =  this.objectBuilder.createStringFromObject(objectToPost);
		try
		{
			sqlQueryManager.executeBulkInsert(sql);
		}
		catch (SQLException e)
		{
			throw new ObjectPersistanceFailedException((RubyModel)objectToPost,e);
		}
		return null;
	}
}
