package nationbuilder.lib.Ruby.configuration;

/**
 * Created by patrick on 7/10/14.
 */

public class RubyConfiguration {

	private String backend;
	private String port;
	private String db_username;
	private String db_password;
	private String db_server;
	private String db_database;
	private String db_tempdir;

	private String classloader_packages;


	public String getBackend()
	{
		return backend;
	}

	public void setBackend(String backend)
	{
		this.backend = backend;
	}

	public String getPort()
	{
		return port;
	}

	public void setPort(String port)
	{
		this.port = port;
	}

	public String getDb_username()
	{
		return db_username;
	}

	public void setDb_username(String db_username)
	{
		this.db_username = db_username;
	}

	public String getDb_password()
	{
		return db_password;
	}

	public void setDb_password(String db_password)
	{
		this.db_password = db_password;
	}

	public String getDb_server()
	{
		return db_server;
	}

	public void setDb_server(String db_server)
	{
		this.db_server = db_server;
	}

	public String getDb_database()
	{
		return db_database;
	}

	public void setDb_database(String db_database)
	{
		this.db_database = db_database;
	}

	public String getDb_tempdir()
	{
		return db_tempdir;
	}

	public void setDb_tempdir(String db_tempdir)
	{
		this.db_tempdir = db_tempdir;
	}

	public String getClassloader_packages()
	{
		return classloader_packages;
	}

	public void setClassloader_packages(String classloader_packages)
	{
		this.classloader_packages = classloader_packages;
	}
}