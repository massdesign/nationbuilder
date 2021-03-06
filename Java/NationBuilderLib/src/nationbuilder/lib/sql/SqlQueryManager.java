package nationbuilder.lib.sql;

import java.io.*;

import com.mysql.jdbc.Statement;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.data.map.ResponseData;
import org.apache.commons.io.IOUtils;


/**
 * Created by patrick on 12/15/14.
 */
public class SqlQueryManager implements QueryManager
{
	String userName;
	String password;
	String serverLocation = "jdbc:mysql://";
    String database  = "";
	String tempdirLocation = "";
    // Ruby ORM assets DB
    String rorm_assets = "rorm_assets";
	Statement stmt = null;
	// kleiner dan 0
	int nextint = -1;
	boolean sequencePersisted = false;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	// TODO: consider packing all those properties into a object
	public SqlQueryManager(String userName,String password,String serverLocation,String database,String tempdirLocation) {

		this.userName = userName;
		this.password = password;
		this.serverLocation += serverLocation;
        this.database = database;
		this.tempdirLocation = tempdirLocation;
		try
		{
			Class.forName(JDBC_DRIVER);
		}
		catch (ClassNotFoundException e)
		{
			Log.write(e, LogType.ERROR);
		}
	}

	public int getCurrentID() throws SQLException
	{

		if(nextint < 0)
		{

			Connection conn = this.createConnection(this.rorm_assets);

			PreparedStatement selectStmt = conn.prepareStatement("SELECT MAX(ruby_object_counter) FROM object_count ");
			selectStmt.execute();

			ResultSet gks = selectStmt.getResultSet();
			gks.next();
			nextint = gks.getInt(1);

		}

		return nextint;
	}

    public int getNextID() throws SQLException
	{
		// first check if we have fetched the current id from the database
		if (nextint > 0) {
			nextint += 1;
			sequencePersisted = false;
		}
        else
		{
			// Altijd eentje ophogen Als je maar een object wegschrijft levert dit een bug op
			nextint = getCurrentID()+1;
		}
        return nextint;
    }

	private void persistCurrentId() throws SQLException
	{
		if(!sequencePersisted)
		{
			Connection conn = this.createConnection(this.rorm_assets);
			PreparedStatement insertStmt = conn
					.prepareStatement("INSERT INTO object_count VALUES(" + getCurrentID() + ")");
			insertStmt.executeUpdate();
			sequencePersisted = true;
		}
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
	public ResponseData executeBulkInsert(List<String> rows,String tableName) throws SQLException
	{
		this.persistCurrentId();
        String filename =  tableName + "_import.sql";
		String path = this.tempdirLocation;

		File dirPath = new File(path);


		if(!dirPath.exists())
		{
			dirPath.mkdir();
		}

			String filepath = path + filename;

			try
			{
				File importFile = new File(filepath);
				if (!importFile.exists())
				{
					importFile.createNewFile();
				}
				PrintWriter writer = new PrintWriter(filepath, "UTF-8");
				for (String sql : rows)
				{
					writer.println(sql);
				}

				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}

			ResponseData responseData = new SqlResponseData();
			Connection conn = createConnection(this.database);

			Statement statement = (Statement) conn.createStatement();
			statement.execute("SET UNIQUE_CHECKS=0;");
			// bij wijze van concept eerst de tiles tabel proberen te inserten
			statement.execute("ALTER TABLE " + tableName + " DISABLE KEYS");

			String query = "LOAD DATA LOCAL INFILE '" + filepath + "' " +
						   "INTO TABLE " + tableName +
						   " FIELDS TERMINATED BY ','";
			statement.execute(query);
			// Create StringBuilder to String that will become stream
			StringBuilder builder = new StringBuilder();
			InputStream is = IOUtils.toInputStream(builder.toString());

			// Setup our input stream as the source for the local infile
			statement.setLocalInfileInputStream(is);
			//Statement stmt = conn.createStatement();
			//int rowsAffected  = stmt.executeUpdate(sql);

			// Turn the checks back on
			statement.execute("ALTER TABLE " + tableName + " ENABLE KEYS");
			statement.execute("SET UNIQUE_CHECKS=1; ");
			conn.close();
			return responseData;
		}

	}

