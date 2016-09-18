package test.IntegrationTests.RubyDaoTests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.http.HttpRequestUtil;
import nationbuilder.lib.http.data.HttpResponseData;

/**
 * @author patrick.ekkel
 */
public class DatabaseTest
{
	private String USERNAME = "root";
	private String PASSWORD = "defcon1986";
	private com.mysql.jdbc.Connection connection;
	protected void createEmpyDatabase()
	{
		HttpResponseData resultCode = null;

		resultCode = HttpRequestUtil.sendGetRequest("http://localhost:8083/resetdb");
		if (resultCode.getResponseCode() == 200)
		{
			Log.writeInfo("database reset successfull");
		}
	}
	protected void createJdbConnection() throws SQLException
	{
	connection = (com.mysql.jdbc.Connection) DriverManager
		 .getConnection("jdbc:mysql://localhost/NationBuilder", USERNAME, PASSWORD);
	}
	protected ResultSet getLastResultFromTable(String tablename) throws SQLException
	{
		PreparedStatement selectStmt = connection.prepareStatement("SELECT * FROM " + tablename + "  WHERE id = (SELECT  MAX(id) FROM  " +  tablename + ") ");
		selectStmt.execute();
		ResultSet gks = selectStmt.getResultSet();
		gks.next();

		return  gks;
	}

}
