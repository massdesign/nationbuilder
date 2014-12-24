package nationbuilder.lib.http.data;

import java.io.InputStream;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.sql.Column;
import nationbuilder.lib.sql.TableData;
import org.apache.commons.io.IOUtils;


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


    public int getNextID() throws SQLException
	{

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
			Connection	conn = (Connection)DriverManager.getConnection(serverLocation + "/" + database, userName, password);
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
	public TableData getTableStructure(String tableName) throws SQLException
	{
		Connection conn = createConnection(this.database);
		Statement statement = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery("DECRIBE " + tableName);
		TableData result = new TableData();
		result.setTable(tableName);
		// TODO: handle field name
		while (rs.next()) {
			String fieldName = rs.getString("Field");
			String type = rs.getString("Type");

			result.addColumn(new Column(fieldName,type));

		}
		conn.close();

		return result;
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
	public ResponseData executeBulkInsert(String sql) throws SQLException
	{
		ResponseData responseData = new SqlResponseData();
		Connection conn = createConnection(this.database);

	    Statement statement  = (Statement) conn.createStatement();
		statement.execute("SET UNIQUE_CHECKS=0;");
		// bij wijze van concept eerst de tiles tabel proberen te inserten
		statement.execute("ALTER TABLE tiles DISABLE KEYS");

		String query = "LOAD DATA LOCAL INFILE 'file.txt' " +
								"INTO TABLE tiles" +
								"(name, value) " +
								" SET owner_id = " + "<TODO>" + ", " +
								" version = 0; ";

		// Create StringBuilder to String that will become stream
		StringBuilder builder = new StringBuilder();
		InputStream is = IOUtils.toInputStream(builder.toString());

		// Setup our input stream as the source for the local infile
		statement.setLocalInfileInputStream(is);
		//Statement stmt = conn.createStatement();
		//int rowsAffected  = stmt.executeUpdate(sql);

		// Turn the checks back on
		statement.execute("ALTER TABLE affinity ENABLE KEYS");
		statement.execute("SET UNIQUE_CHECKS=1; ");
		conn.close();
		return responseData;
	}
}
