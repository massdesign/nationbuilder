package test.IntegrationTests.RubyDaoTests;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import nationbuilder.lib.Ruby.DefaultRubyContextFactory;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.PowerConnection;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author patrick.ekkel
 */
public class PowerConnectionDaoTest extends DatabaseTest
{
	@Test
	public void createPowerConnection() throws RubyException, SQLException
	{
		createEmpyDatabase();
		RubyContext rubyContext = new DefaultRubyContextFactory()
		 .createRubyContext(RubyContextType.JSON, getClass());

	     PowerConnection powerConnection = 	rubyContext.createRubyModel(PowerConnection.class);
		 PowerGridNode powerGridNode1 = rubyContext.createRubyModel(PowerGridNode.class);
		 PowerGridNode powerGridNode2 = rubyContext.createRubyModel(PowerGridNode.class);

		 powerConnection.setA(powerGridNode1);
		 powerConnection.setB(powerGridNode2);

		 powerConnection.Save();

		Connection conn = (Connection) DriverManager
		 .getConnection("jdbc:mysql://localhost/NationBuilder", "root", "defcon1986");
		PreparedStatement selectStmt = conn.prepareStatement("SELECT * FROM power_connections  WHERE id = (SELECT  MAX(id) FROM  power_connections) ");
		selectStmt.execute();
		ResultSet gks = selectStmt.getResultSet();
		gks.next();

		// check if we have a record  stored with the same id
		String powerconnection_actual_id = String.valueOf(gks.getInt(1));
		String powerconnection_expected_id = powerConnection.getId().getId();
		Assert.assertTrue(powerconnection_actual_id.equals(powerconnection_expected_id));

		// check if has references to the powergridnodes
		String powergridnode_a_id_expected = powerConnection.getA().getId().getId();
		String powergridnode_b_id_expected = powerConnection.getB().getId().getId();

		String powergridnode_a_id_actual = String.valueOf(gks.getInt(5));
		String powergridnode_b_id_actual = String.valueOf(gks.getInt(6));

		Assert.assertTrue(powergridnode_a_id_expected.equals(powergridnode_a_id_actual));
		Assert.assertTrue(powergridnode_b_id_expected.equals(powergridnode_b_id_actual));
	}


}
