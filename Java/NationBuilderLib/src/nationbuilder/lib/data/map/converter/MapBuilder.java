package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.xml.TiledXmlMap;

/**
 * Created by patrick on 5/19/16.
 */
public class MapBuilder {

    private TilesetBuilder tilesetBuilder;
    private XmlTileBuilder xmlTileBuilder;

    public MapBuilder(TilesetBuilder tilesetBuilder,XmlTileBuilder xmlTileBuilder) {
        this.tilesetBuilder = tilesetBuilder;
        this.xmlTileBuilder = xmlTileBuilder;
    }


    public MapMap convertMap(TiledXmlMap map)
    {
        MapMap result = new MapMap();
        result.setHeight(map.getHeight());
        result.setWidth(map.getWidth());
        result.setTileHeight(map.getTileHeight());
        result.setTileWidth(map.getTileWidth());
        return result;
    }
}
