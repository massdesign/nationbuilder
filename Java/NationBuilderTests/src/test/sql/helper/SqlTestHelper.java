package test.sql.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author patrick.ekkel
 */
public class SqlTestHelper
{


	public static Connection createSqlConnection(String serverLocation,String database,String userName,String password) throws SQLException
	{

		Connection connection =  (com.mysql.jdbc.Connection) DriverManager
		 .getConnection(serverLocation + "/" + database, userName, password);

		return connection;
	}



}
