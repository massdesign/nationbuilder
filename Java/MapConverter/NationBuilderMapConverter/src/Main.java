
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
        // first run the filler to create all the essential datbase stuff
        Filler filler = new Filler(context);
        filler.Fill();

		TiledXmlMapFactory tiledXmlMapFactory = new TiledXmlMapFactory();

		TiledXmlMap tiledXmlMap = tiledXmlMapFactory.createTiledXmlMap("/home/patrick/Git/nationbuilder/Tiled/Maps/demo_small.tmx");

		TiledMapConverter converter = new TiledMapConverter(tiledXmlMap,context);
		converter.Convert();
		MapDataset dataset = converter.GetMapDataset();
		MapServiceConnector mapsServiceConnector = new MapServiceConnector(context);
		mapsServiceConnector.addDataset(dataset);

   	  }
	}
	

