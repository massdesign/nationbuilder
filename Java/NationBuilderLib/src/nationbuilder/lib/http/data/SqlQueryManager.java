package nationbuilder.lib.http.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;


/**
 * Created by patrick on 12/15/14.
 */
public class SqlQueryManager
{
	String userName;
	String password;
	String serverLocation = "jdbc:mysql://";

	Statement stmt = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public SqlQueryManager(String userName,String password,String serverLocation) {

		this.userName = userName;
		this.password = password;
		this.serverLocation += serverLocation;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException e)
		{
			Log.write(e, LogType.ERROR);
		}
	}

	private Connection createConnection() {

		try
		{
			Connection	conn = DriverManager.getConnection(serverLocation, userName, password);
			return conn;
		}
		catch (SQLException e)
		{
			Log.write(e,LogType.ERROR);
			return null;
		}
	}
	public void executeInsert(String sql)
	{
		Connection conn = createConnection();
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			Log.write(e,LogType.ERROR);
		}

		// TODO: implement
	}
	public void executeUpdate(String sql)
	{
		Connection conn = createConnection();
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			Log.write(e, LogType.ERROR);
		}
	}
	public HttpResponseData executeSelect(String sql)
	{
		Connection conn = createConnection();
		try
		{
			conn.close();
		}
		catch (SQLException e)
		{
			Log.write(e, LogType.ERROR);
		}
		HttpResponseData responseData = null;

		return responseData;
	}
	// NOTE: created two methods because i don't know if we need to handle bulk inserts differently than normal inserts
	public ResponseData executeBulkInsert(String sql) throws SQLException
	{
		ResponseData responseData = new SqlResponseData();
		Connection conn = createConnection();
		Statement stmt = conn.createStatement();
		int rowsAffected  = stmt.executeUpdate(sql);
		conn.close();
		return responseData;
	}
}
