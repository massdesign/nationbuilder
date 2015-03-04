
import java.io.IOException;

import World.PreFiller;
import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.data.map.entities.City;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.converter.TiledMapConverter;
import nationbuilder.lib.data.map.entities.WareHouse;
import nationbuilder.lib.data.map.mapservice.MapServiceConnector;
import nationbuilder.lib.data.map.xml.Configuration;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.TiledXmlMapFactory;


public class Main {
	public static void main(String[] args) throws IOException {

        RubyContext context = new RubyContextFactory().createRubyContext();
        // first run the filler to create all the essential datbase stuff
       // PreFiller filler = new PreFiller(context);
      //  filler.testFill();
      //  filler.Fill();

	//	TiledXmlMapFactory tiledXmlMapFactory = new TiledXmlMapFactory();

	//	TiledXmlMap tiledXmlMap = tiledXmlMapFactory.createTiledXmlMap(Configuration.SmallDemoMap);

	//	TiledMapConverter converter = new TiledMapConverter(tiledXmlMap,context);
	//	converter.Convert();
	//	MapDataset dataset = converter.GetMapDataset();
		//MapServiceConnector mapsServiceConnector = new MapServiceConnector(context);
		//mapsServiceConnector.addDataset(dataset);
      //  PreFiller f = new PreFiller(new RubyContextFactory().createRubyContext());
       // f.testFill();

      //  WorldLoader worldLoader = new WorldLoader(context);
      //  worldLoader.Run();

	//	EnergyBuilding energyBuilding = context.createRubyModel(EnergyBuilding.class);
	//	energyBuilding.setName("");

		WareHouse wareHouse = context.createRubyModel(WareHouse.class);
		City city = context.createRubyModel(City.class);
		wareHouse.setName("The City Warehouse");
		//city.addBuilding(energyBuilding);
		city.setName("Utrecht");
		city.setPopulation(1000);
		wareHouse.setOwner(city);
		try
		{
			city.Save("/cities/");
			wareHouse.Save("/warehouses/");
		}
		catch (RubyException e)
		{
			e.printStackTrace();
		}
	}
	}
	

