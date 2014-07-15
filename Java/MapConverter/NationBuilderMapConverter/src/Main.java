
import java.io.IOException;

import World.Filler;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.converter.TiledMapConverter;
import nationbuilder.lib.data.map.mapservice.MapServiceConnector;
import nationbuilder.lib.data.map.xml.Property;
import nationbuilder.lib.data.map.xml.Tile;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.TiledXmlMapFactory;




public class Main {
	public static void main(String[] args) throws IOException {

        RubyContext context = new RubyContextFactory().createRubyContext();

		TiledXmlMapFactory tiledXmlMapFactory = new TiledXmlMapFactory();

		TiledXmlMap tiledXmlMap = tiledXmlMapFactory.createTiledXmlMap("/home/patrick/Git/nationbuilder/Tiled/Maps/overview.tmx");

		TiledMapConverter converter = new TiledMapConverter(tiledXmlMap,context);
		converter.Convert();
		MapDataset dataset = converter.GetMapDataset();
		MapServiceConnector mapsServiceConnector = new MapServiceConnector(context);
        //MapServiceConnector mapsServiceConnector = new MapServiceConnector("http://localhost:3000");
		mapsServiceConnector.addDataset(dataset);


        Filler filler = new Filler(context);
        filler.Fill();
   	  }
	}
	

