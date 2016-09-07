package World;

import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Layer;
import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.entities.PowerConnection;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.entities.PowerRelayStationType;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.entities.WareHouse;

/**
 * @author patrick.ekkel
 */
public class TestFiller
{
	private RubyContext context;


	public TestFiller(RubyContext rubyContext) {

	this.context  = rubyContext;
	}

	public void testFillPowerRelayStations() {
		PowerRelayStation powerRelayStation1 = context.createRubyModel(PowerRelayStation.class);

		PowerRelayStation powerRelayStation2 = context.createRubyModel(PowerRelayStation.class);

		powerRelayStation1.setName("PRS01");
		powerRelayStation2.setName("PRS02");

		PowerGridNode powerGridNode1 = context.createRubyModel(PowerGridNode.class);
		PowerGridNode powerGridNode2 = context.createRubyModel(PowerGridNode.class);

		PowerGridNode powerGridNode3 = context.createRubyModel(PowerGridNode.class);

		WareHouse wareHouse = context.createRubyModel(WareHouse.class);
		wareHouse.setName("even testen hoor");

		PowerRelayStation powerRelayStation3 = context.createRubyModel(PowerRelayStation.class);

		powerRelayStation3.setName("testje voor je moeder");

		powerGridNode1.setName("Lakeside power grid node");
		powerGridNode1.setDestroyable(true);
		powerGridNode2.setName("Countryside power grid node");

		PowerRelayStationType powerRelayStationType = context.createRubyModel(PowerRelayStationType.class);
		powerRelayStationType.setName("Dynatec Standard 300MW power distribution station");
		powerRelayStationType.setCapacity(300);

		PowerConnection powerConnection = context.createRubyModel(PowerConnection.class);

		powerConnection.setA(powerGridNode1);
		powerConnection.setB(powerGridNode2);

		powerConnection.setName("Test connection");
		powerConnection.setLoad(21);
		powerConnection.setCapacity(300);

		powerRelayStation1.setPowerRelayStationType(powerRelayStationType);
		powerRelayStation2.setPowerRelayStationType(powerRelayStationType);
		powerGridNode1.setRelayStation(powerRelayStation1);
		powerGridNode2.setRelayStation(powerRelayStation2);

		powerGridNode3.setName("test in je moeder");
		powerGridNode3.setDestroyable(true);
			//   wareHouse.Save("warehouses");
         /*   powerRelayStation3.Save("buildings");
        powerGridNode3.Save("power_grid_nodes");

            powerRelayStationType.Save("power_relay_station_types");
            powerRelayStation1.Save("power_relay_stations");
            powerRelayStation2.Save("power_relay_stations");
            powerGridNode1.Save("power_grid_nodes");
            powerGridNode2.Save("power_grid_nodes");
            powerConnection.Save("power_connections");


            this.context.commit();
        }
        catch (RubyException e)
        {
            e.printStackTrace();
        }
	} */

		// test12345
	}
	public void testFillSmallNationbuilderDatastructure() {

		try
		{
			Thread.sleep(3000);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		MapMap map = this.context.createRubyModel(MapMap.class);
		Layer layer = this.context.createRubyModel(Layer.class);
		Tile tile1 = this.context.createRubyModel(Tile.class);
		Tile tile2 = this.context.createRubyModel(Tile.class);

		TerrainType terrainType = this.context.createRubyModel(TerrainType.class);

		tile1.setTerrainType(terrainType);
		tile2.setTerrainType(terrainType);
		layer.addTile(tile1);
		layer.addTile(tile2);
		map.addLayer(layer);

		try
		{
			map.Save();
			this.context.commit();
		}
		catch (RubyException e)
		{
			e.printStackTrace();
		}


	}
}
