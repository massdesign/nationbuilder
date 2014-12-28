package nationbuilder.lib.http.data;

import java.io.FileNotFoundException;
import java.io.InputStream;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.sql.ColumnMetaData;
import nationbuilder.lib.sql.TableMetaData;
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
        this.database = database;
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
	public TableMetaData getTableStructure(String tableName) throws SQLException
	{
		Connection conn = createConnection(this.database);
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery("DESCRIBE " + tableName);
		TableMetaData result = new TableMetaData();
		result.setTable(tableName);
		// TODO: handle field name
		while (rs.next()) {
			String fieldName = rs.getString("Field");
			String type = rs.getString("Type");

			result.addColumn(new ColumnMetaData(fieldName,type));
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
	public ResponseData executeBulkInsert(List<String> rows) throws SQLException
	{
        String filename = "import.sql";
        String path = "/home/patrick/Git/nationbuilder/Temp/";

        String filepath = path + filename;


        try {
            PrintWriter writer = new PrintWriter(filepath,"UTF-8");
            for(String sql : rows)
            {
                writer.println(sql);
            }

            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        ResponseData responseData = new SqlResponseData();
		Connection conn = createConnection(this.database);

	    Statement statement  = (Statement) conn.createStatement();
		statement.execute("SET UNIQUE_CHECKS=0;");
		// bij wijze van concept eerst de tiles tabel proberen te inserten
		statement.execute("ALTER TABLE tiles DISABLE KEYS");

		String query = "LOAD DATA LOCAL INFILE '" + filepath + "' " +
								"INTO TABLE tiles" +
                                " FIELDS TERMINATED BY ','" +
                                "  (id,gidtag,xposition,yposition,xoffset,yoffset,created_at,updated_at,layer_id,image_id,resource_id)";

        statement.execute(query);
		// Create StringBuilder to String that will become stream
		StringBuilder builder = new StringBuilder();
		InputStream is = IOUtils.toInputStream(builder.toString());

		// Setup our input stream as the source for the local infile
		statement.setLocalInfileInputStream(is);
		//Statement stmt = conn.createStatement();
		//int rowsAffected  = stmt.executeUpdate(sql);

		// Turn the checks back on
		statement.execute("ALTER TABLE tiles ENABLE KEYS");
		statement.execute("SET UNIQUE_CHECKS=1; ");
		conn.close();
		return responseData;
	}
}
