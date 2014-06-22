
import java.io.IOException;
import nationbuilder.lib.data.map.MapDataset;
import nationbuilder.lib.data.map.MapTile;
import nationbuilder.lib.data.map.converter.TiledMapConverter;
import nationbuilder.lib.data.map.mapservice.MapServiceConnector;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.TiledXmlMapFactory;




public class Main {
	private final static String USER_AGENT = "Mozilla/5.0";
	public static void main(String[] args) throws IOException {
		TiledXmlMapFactory tiledXmlMapFactory = new TiledXmlMapFactory();

		TiledXmlMap tiledXmlMap = tiledXmlMapFactory.createTiledXmlMap("/home/patrick/SVN/NationBuilder/Tiled/Maps/overview.tmx");
		TiledMapConverter converter = new TiledMapConverter(tiledXmlMap);
		converter.Convert();
		MapDataset dataset = converter.GetMapDataset();		
	//	MapTile tile =  dataset.getMapTiles().get(0);
	//	MapServiceConnector mapsServiceConnector = new MapServiceConnector("http://192.168.1.6:3000");
        MapServiceConnector mapsServiceConnector = new MapServiceConnector("http://localhost:3000");
		mapsServiceConnector.addDataset(dataset);
   	  }
	}
	

