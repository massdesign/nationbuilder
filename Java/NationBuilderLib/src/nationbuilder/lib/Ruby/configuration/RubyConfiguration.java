package nationbuilder.lib.Ruby.configuration;

/**
 * Created by patrick on 7/10/14.
 */

// TODO: if the need arises, we need to get rid of this configClass.. Create a propertiesfile that also allows for default
public class RubyConfiguration {
	// Topicus laptop config
	  public static String RubyBackend = "http://localhost";
	// ThuisConfig
  //  public static String RubyBackend = "http://192.168.1.6";
    public static String RubyBackendPort = "3000";

	public static String mySqlUsername = "root";
	public static String mySqlPassword = "defcon1986";
	public static String mySqlServer = "localhost";
    public static String mySqlDatabase  = "NationBuilder";
	public static String mySqlTempDir = "/home/patrick/Git/nationbuilder/Temp/";
}