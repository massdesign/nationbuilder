package nationbuilder.lib.sql;

import nationbuilder.lib.Ruby.configuration.RubyConfiguration;
import nationbuilder.lib.data.map.xml.RubyDIPropertyLoader;

/**
 * Created by patrick on 12/22/14.
 */
public class SqlQueryManagerFactory
{
	public SqlQueryManager createQueryManager(RubyDIPropertyLoader rubyDIPropertyLoader)
	{
		// TODO: dit refactoren naar een property die meegegeven wordt niet 10 properties die in de constructor geset worden
		SqlQueryManager result = new SqlQueryManager(rubyDIPropertyLoader.getRubyConfiguration().getDb_username(),rubyDIPropertyLoader.getRubyConfiguration().getDb_password(),
		 rubyDIPropertyLoader.getRubyConfiguration().getDb_server(), rubyDIPropertyLoader.getRubyConfiguration().getDb_database(),rubyDIPropertyLoader.getRubyConfiguration().getDb_tempdir());

		return result;
	}
}
