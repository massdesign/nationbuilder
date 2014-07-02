
import java.io.IOException;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.converter.TiledMapConverter;
import nationbuilder.lib.data.map.mapservice.MapServiceConnector;
import nationbuilder.lib.data.map.xml.Property;
import nationbuilder.lib.data.map.xml.Tile;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.TiledXmlMapFactory;




public class Main {
	private final static String USER_AGENT = "Mozilla/5.0";
	public static void main(String[] args) throws IOException {
		TiledXmlMapFactory tiledXmlMapFactory = new TiledXmlMapFactory();

		TiledXmlMap tiledXmlMap = tiledXmlMapFactory.createTiledXmlMap("/home/patrick/Git/nationbuilder/Tiled/Maps/demo.tmx");


	    Tile tile =	tiledXmlMap.getTilesets().get(0).getTiles().get(0);


        Property property = tile.getProperties().get(0);

        System.out.println(property.getName() +  " : " + property.getValue());
		TiledMapConverter converter = new TiledMapConverter(tiledXmlMap);
		converter.Convert();
		MapDataset dataset = converter.GetMapDataset();
	//	MapTile tile =  dataset.getMapTiles().get(0);
		MapServiceConnector mapsServiceConnector = new MapServiceConnector("http://192.168.1.6:3000");
        //MapServiceConnector mapsServiceConnector = new MapServiceConnector("http://localhost:3000");
		mapsServiceConnector.addDataset(dataset);
   	  }
	}
	

