package test.IntegrationTests.RubyJsonDaoTests;

import java.sql.ResultSet;
import java.sql.SQLException;
import nationbuilder.lib.Ruby.DefaultRubyContextFactory;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
import nationbuilder.lib.data.map.entities.EnergyBuildingType;
import nationbuilder.lib.data.map.entities.PowerConnection;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.entities.PowerRelayStationType;
import nationbuilder.lib.data.map.entities.Tile;
import org.junit.Before;
import test.IntegrationTests.RubyDaoTests.DatabaseTest;
import org.junit.Test;

/**
 * @author patrick.ekkel
 */
public class PowerConnectionJsonTest extends DatabaseTest
{
	@Before
	public void setup() throws RubyException
	{
		createEmpyDatabase();
		// Wat test data injecteren in de database
		createTestData();
	}

	@Test
	public void energyBuildingTest() throws RubyException, SQLException
	{
		// Test if energybuildings are correctly made
		ResultSet energy_buildings = getAllResultsFromTable("energy_buildings");





	}


	public void createTestData() throws RubyException
	{
		// TODO: power_grid_node_id wordt niet goed gezet op tabel energy_building

		RubyContext rubyContext = new DefaultRubyContextFactory().createRubyContext(RubyContextType.JSON, getClass());

		EnergyBuildingType energyBuildingType = rubyContext.createRubyModel(EnergyBuildingType.class);
		energyBuildingType.setName("Energybuildingtype MK1");
		energyBuildingType.setPowerOutput(400);
		energyBuildingType.setResponsetime("FAST");
		energyBuildingType.Save();

		PowerGridNode powerGridNode1 = rubyContext.createRubyModel(PowerGridNode.class);
		powerGridNode1.setName("PGN1");


		EnergyBuilding energyBuilding = rubyContext.createRubyModel(EnergyBuilding.class);
		energyBuilding.setName("Energybuilding 1");
		energyBuilding.setBuildingType(energyBuildingType);
		energyBuilding.setPowerGridNode(powerGridNode1);
		energyBuilding.Save();
		powerGridNode1.Save();
		Tile tile1 = rubyContext.createRubyModel(Tile.class);


		tile1.setXoffset(1);
		tile1.setYoffset(1);
		tile1.setXposition(3);
		tile1.setYposition(4);

		tile1.addBuilding(energyBuilding);
		tile1.Save();

		PowerRelayStationType powerRelayStationType = rubyContext.createRubyModel(PowerRelayStationType.class);
		powerRelayStationType.setCapacity(500);
		powerRelayStationType.setName("Powerrelaystation MK1");
		powerRelayStationType.Save();

		PowerGridNode powerGridNode2 = rubyContext.createRubyModel(PowerGridNode.class);
		powerGridNode2.setName("PGN2");


		PowerRelayStation powerRelayStation = rubyContext.createRubyModel(PowerRelayStation.class);
		powerRelayStation.setPowerGridNode(powerGridNode2);
		powerRelayStation.setPowerRelayStationType(powerRelayStationType);
		powerRelayStation.setName("VRSHP_1");

		Tile tile2 = rubyContext.createRubyModel(Tile.class);
		tile2.setXoffset(0);
		tile2.setYoffset(0);
		tile2.setXposition(2);
		tile2.setYposition(2);
		tile2.addBuilding(powerRelayStation);

		PowerConnection powerConnection = rubyContext.createRubyModel(PowerConnection.class);

		powerConnection.setA(powerGridNode1);
		powerConnection.setB(powerGridNode2);
		//powerConnection.Save();
	}

}
