package nationbuilder.lib.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlServiceConnector {

	
	
	String url;
	String dbName;
	String userName;
	String host = "";
	String password;
	Connection conn = null;	
	private static String DRIVER = "com.mysql.jdbc.Driver";
	public MySqlServiceConnector()
	{
	
	}
	public MySqlServiceConnector(String host,String dbName,String userName,String password) throws InstantiationException, IllegalAccessException, ClassNotFoundException
	{
		Class.forName(DRIVER).newInstance();
		
		
		this.setHost(host);
		this.password = password;
		this.dbName = dbName;
		this.userName = userName;
	}
	public void connect() throws SQLException
	{
		conn = DriverManager.getConnection(this.host + dbName,this.userName,this.password);
	}
	public String getHost() {
		return url;
	}
	public void setHost(String host) {
		this.host = String.format("jdbc:mysql://%s/",host);
	}
	public String getDbName() {
		return dbName;
	}
	public void setDbName(String dbName) {
		this.dbName = dbName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	
}
