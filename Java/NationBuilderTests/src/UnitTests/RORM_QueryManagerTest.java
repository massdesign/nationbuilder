package UnitTests;

import java.sql.SQLException;
import nationbuilder.lib.sql.SqlQueryManager;
import nationbuilder.lib.sql.TableMetaData;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * @author patrick.ekkel
 */
public class RORM_QueryManagerTest
{

	private static String username = "root";
	private static String password = "defcon1986";
	private static String location = "localhost";
	private static String database = "rorm_tests";
	private static String tempdir = "/tmp";

	@Before
	public void setup() throws SQLException
	{

		// create a script here that will deploy the test database

		/*Connection conn = SqlTestHelper.createSqlConnection(location,database,username,password);

		Statement statement = conn.createStatement();

		statement.executeQuery(""); */

	}

	@After
	public void tearDown() {

	}



	@Test
	public void queryManagerTableStructureTest() throws SQLException
	{

		SqlQueryManager sqlQueryManager = new SqlQueryManager(username,password,location,database,tempdir);
		TableMetaData tableMetaData = sqlQueryManager.getTableStructure("testtable");
		int expected = 4;
		int actual = tableMetaData.getSortedColumns().size();
		Assert.assertEquals(expected,actual);

		Assert.assertEquals("a",tableMetaData.getSortedColumns().get(0).getColumnName());
		Assert.assertEquals("b",tableMetaData.getSortedColumns().get(1).getColumnName());
		Assert.assertEquals("c",tableMetaData.getSortedColumns().get(2).getColumnName());
		Assert.assertEquals("d",tableMetaData.getSortedColumns().get(3).getColumnName());

		Assert.assertEquals("varchar(11)", tableMetaData.getSortedColumns().get(0).getColumnType());
		Assert.assertEquals("text", tableMetaData.getSortedColumns().get(1).getColumnType());
		Assert.assertEquals("int(11)", tableMetaData.getSortedColumns().get(2).getColumnType());
		Assert.assertEquals("int(11)", tableMetaData.getSortedColumns().get(3).getColumnType());
	}
	@Test
	public void queryManagerNextIdTest() throws SQLException
	{
		SqlQueryManager sqlQueryManager = new SqlQueryManager(username, password, location, database,tempdir);

		int currentid = sqlQueryManager.getCurrentID();

		int nextid = sqlQueryManager.getNextID();

		int actual = nextid;
		int expected = currentid + 1;
		Assert.assertEquals(actual,expected);

	}
	@Ignore
	@Test
	public void queryManagerBulkInsertTest() throws SQLException
	{
		SqlQueryManager sqlQueryManager = new SqlQueryManager(username, password, location, database,"/tmp");

		sqlQueryManager.executeBulkInsert(null,"");

	}

}
