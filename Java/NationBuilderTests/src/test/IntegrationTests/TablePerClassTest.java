package test.IntegrationTests;


import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mocks.QueryManagerMock;
import mocks.RubyMockService;
import mocks.TPCTestModel1;
import mocks.TPCTestmodel2;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.Ruby.RubyServiceImpl;
import nationbuilder.lib.Ruby.services.ResolveUnresolvedFieldsService;
import nationbuilder.lib.connectors.JsonObjectBuilder;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.json.connectors.JsonServiceConnector;
import nationbuilder.lib.sql.QueryManager;
import nationbuilder.lib.sql.SqlQueryManager;
import nationbuilder.lib.sql.SqlQueryManagerFactory;
import nationbuilder.lib.sql.connectors.SqlServiceConnector;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
/**
 * @author patrick.ekkel
 */
public class TablePerClassTest
{
	@Test
	public void TestTablePerClass() throws RubyException, SQLException
	{
		RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT,getClass());
		TPCTestmodel2 testmodel1 =   context.createRubyModel(TPCTestmodel2.class);
		testmodel1.Save("tpctestmodel2");
		context.commit();
		String  expectedReferencedObjectId  =   testmodel1.getTpctestmodel2_id().getID().getId();
		String  expectedId = testmodel1.getId().getId();

		//  Voer een plainSql query  uit  om  te checken of  the  TablePerClassStrategy goed op de database is uitgevoerd
		Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/MockDatabase", "root", "defcon1986");
		PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM tpc_testmodel1  WHERE id = (SELECT  MAX(id) FROM  tpc_testmodel1) ");
		selectStmt.execute();
		ResultSet gks = selectStmt.getResultSet();
		gks.next();

		String tpcmodel1_realid  =   String.valueOf(gks.getInt(1));
		String tpcmodel2_realid  =  String.valueOf(gks.getInt(3));

		Assert.assertEquals(tpcmodel1_realid,expectedId);
		Assert.assertEquals(tpcmodel2_realid,expectedReferencedObjectId);





	}




}
