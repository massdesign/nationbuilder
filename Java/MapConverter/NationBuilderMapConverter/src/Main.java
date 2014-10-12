
import java.io.DataOutputStream;
import java.io.IOException;

import World.Filler;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.converter.TiledMapConverter;
import nationbuilder.lib.data.map.entities.MapImage;
import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.entities.MapTile;
import nationbuilder.lib.data.map.entities.Resource;
import nationbuilder.lib.data.map.entities.ResourceType;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;
import nationbuilder.lib.data.map.mapservice.MapServiceConnector;
import nationbuilder.lib.data.map.xml.Configuration;
import nationbuilder.lib.data.map.xml.Image;
import nationbuilder.lib.data.map.xml.Property;
import nationbuilder.lib.data.map.xml.Tile;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.TiledXmlMapFactory;
import nationbuilder.lib.http.HttpRequest;


public class Main {
	public static void main(String[] args) throws IOException {

        RubyContext context = new RubyContextFactory().createRubyContext();
        // first run the filler to create all the essential datbase stuff
        Filler filler = new Filler(context);
       // filler.testFill();
        filler.Fill();

		TiledXmlMapFactory tiledXmlMapFactory = new TiledXmlMapFactory();
		TiledXmlMap tiledXmlMap = tiledXmlMapFactory.createTiledXmlMap(Configuration.SmallDemoMap);
		TiledMapConverter converter = new TiledMapConverter(tiledXmlMap,context);
		converter.Convert();
		MapDataset dataset = converter.GetMapDataset();
		MapServiceConnector mapsServiceConnector = new MapServiceConnector(context);
		mapsServiceConnector.addDataset(dataset);
   	  }
	}
	

