package nationbuilder.lib.http.data;

import java.sql.*;

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
    String database  = "";
    // Ruby ORM assets DB
    String rorm_assets = "rorm_assets";
	Statement stmt = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	public SqlQueryManager(String userName,String password,String serverLocation,String database) {

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


    public int getNextID() throws SQLException {

        int nextint = 0;
        Connection conn = this.createConnection(this.rorm_assets);

        PreparedStatement insertStmt = conn.prepareStatement("INSERT INTO object_count VALUES()",
                Statement.RETURN_GENERATED_KEYS);
            insertStmt.executeUpdate();
            ResultSet gks = insertStmt.getGeneratedKeys();
            gks.next();
            nextint = gks.getInt(1);

        conn.close();
        return nextint;
    }


	private Connection createConnection(String database) {

		try
		{
			Connection	conn = DriverManager.getConnection(serverLocation + "/" + database, userName, password);
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
		Connection conn = createConnection(this.database);
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
		Connection conn = createConnection(this.database);
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
		Connection conn = createConnection(this.database);
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
		Connection conn = createConnection(this.database);


		//Statement stmt = conn.createStatement();
		//int rowsAffected  = stmt.executeUpdate(sql);
		conn.close();
		return responseData;
	}
}
