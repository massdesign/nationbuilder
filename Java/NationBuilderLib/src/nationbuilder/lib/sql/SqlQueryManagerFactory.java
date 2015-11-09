package nationbuilder.lib.sql;

import nationbuilder.lib.Ruby.configuration.RubyConfiguration;

/**
 * Created by patrick on 12/22/14.
 */
public class SqlQueryManagerFactory
{


	public SqlQueryManager createQueryManager()
	{
		SqlQueryManager result = new SqlQueryManager(RubyConfiguration.mySqlUsername, RubyConfiguration.mySqlPassword,
		 RubyConfiguration.mySqlServer, RubyConfiguration.mySqlDatabase,RubyConfiguration.mySqlTempDir);

		return result;
	}
}
