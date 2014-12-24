package nationbuilder.lib.http.data;

import nationbuilder.lib.Ruby.RubyConfiguration;

/**
 * Created by patrick on 12/22/14.
 */
public class SqlQueryManagerFactory
{


	public SqlQueryManager createQueryManager()
	{
		SqlQueryManager result = new SqlQueryManager(RubyConfiguration.mySqlUsername, RubyConfiguration.mySqlPassword,
		 RubyConfiguration.mySqlServer, RubyConfiguration.mySqlDatabase);

		return result;
	}
}
