package nationbuilder.lib.data.map.converter;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.builders.LayerBuilder;
import nationbuilder.lib.data.map.builders.MapBuilder;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.exceptions.MapConvertException;
import nationbuilder.lib.data.map.xml.*;


// TODO: deze methode volledig verwijderen.. Het meeste wat hier nog gebeurt is niet meer nodig..
public class TiledMapConverter {

    TiledXmlMap xmlMap;
    ArrayList<Image> mapImages;
    MapMap map;
    ArrayList<Layer> layers;
    ArrayList<Tile> mapTiles;
    RubyContext rubyContext;
    public TiledMapConverter(TiledXmlMap xmlMap,RubyContext context)
    {
        this();
        this.xmlMap = xmlMap;
        this.rubyContext = context;
    }
    public TiledMapConverter()
    {
        this.layers = new ArrayList<>();
    }

    public MapDataset GetMapDataset()
    {
        MapDataset result = new MapDataset();
        result.setMap(this.map);
        result.setMapImages(this.mapImages);
        result.setMapTiles(this.mapTiles);
        result.setMapLayers(this.layers);
        return result;
    }
    public void Convert()
    {

        MapBuilder mapBuilder = new MapBuilder(this.rubyContext);
        try
        {
            this.map = mapBuilder.createMap(this.xmlMap);
        }
        catch (MapConvertException e)
        {
            e.printStackTrace();
        }
        mapBuilder.Persist();
    }

}
