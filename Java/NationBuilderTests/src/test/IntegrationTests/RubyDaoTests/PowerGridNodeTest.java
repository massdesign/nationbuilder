package test.IntegrationTests.RubyDaoTests;

import java.sql.ResultSet;
import java.sql.SQLException;
import nationbuilder.lib.Ruby.DefaultRubyContextFactory;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Result;

/**
 * @author patrick.ekkel
 */
public class PowerGridNodeTest extends DatabaseTest
{
	@Test
	public void createPowerGridNode() throws RubyException, SQLException
	{

		createEmpyDatabase();
		RubyContext rubyContext = new DefaultRubyContextFactory().createRubyContext(RubyContextType.JSON, getClass());
		PowerGridNode powerGridNode = rubyContext.createRubyModel(PowerGridNode.class);
		powerGridNode.setName("test");

		powerGridNode.Save();

		createJdbConnection();

		ResultSet resultSet_powergrid_node = getLastResultFromTable("power_grid_nodes");
		ResultSet resultSet_node_type = getLastResultFromTable("node_types");

		String actual_id = String.valueOf(resultSet_powergrid_node.getInt(1));
		String expected_id = powerGridNode.getId().getId();

		String actual_name = resultSet_node_type.getString(2);
		String expected_name = powerGridNode.getName();

		Assert.assertTrue(actual_id.equals(expected_id));
		Assert.assertTrue(actual_name.equals(expected_name));

	}


}
