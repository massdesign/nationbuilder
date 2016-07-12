package nationbuilder.lib.data.map.xml;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.configuration.RubyConfiguration;

/**
 * @author patrick.ekkel
 */
public class RubyDIPropertyLoader
{
	private RubyConfiguration rubyConfiguration;
	public void load(Class clazz) {

		Properties properties = new Properties();
	  	InputStream inputStream = clazz.getClassLoader().getResourceAsStream("config.properties");

		try
		{
			if(inputStream != null)
			{
				properties.load(inputStream);
			}
			else {
				FileInputStream fileInputStream = new FileInputStream("config.properties");
				properties.load(fileInputStream);
			}
			parseProperties(properties);

		}
		catch (IOException e)
		{
			Log.write(e, LogType.ERROR);
		}
	}
	private void parseProperties(Properties properties) {

		this.rubyConfiguration = new RubyConfiguration();
		this.rubyConfiguration.setBackend((String)properties.get("backend"));
		this.rubyConfiguration.setPort((String)properties.get("port"));
		this.rubyConfiguration.setDb_server((String)properties.get("db_server"));
		this.rubyConfiguration.setDb_password((String)properties.get("db_password"));
		this.rubyConfiguration.setDb_database((String)properties.get("db_database"));
		this.rubyConfiguration.setDb_tempdir((String)properties.get("rubydi_tempdir"));
		this.rubyConfiguration.setDb_username((String)properties.get("db_username"));
		this.rubyConfiguration.setClassloader_packages((String)properties.get("rubydi_classloader_packages"));
	}

	public RubyConfiguration getRubyConfiguration() {

		return this.rubyConfiguration;

	}
}
