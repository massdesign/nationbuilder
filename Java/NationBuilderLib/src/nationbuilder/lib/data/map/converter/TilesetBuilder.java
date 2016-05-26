package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.data.map.xml.TileSet;
import nationbuilder.lib.data.map.xml.XmlTile;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by patrick on 5/16/16.
 */
public class TilesetBuilder {

    private ImageBuilder imageBuilder;
    private HashMap<Integer,XmlTile> tilesWithProperties;


    public TilesetBuilder() {
        this.tilesWithProperties = new HashMap<>();
    }

    public void addTileset(TileSet tileSet)
    {
        ArrayList<XmlTile> tiles = tileSet.getTiles();
        // TODO: deze manier van id's mappen is fout, in Tiled tiles zijn alleen uniek binnen tileset
        for(XmlTile tile : tiles)
        {
            int calculatedGid =  tileSet.getFirstGid()+tile.getGID();
            if(!this.tilesWithProperties.containsKey(calculatedGid))
            {
                this.tilesWithProperties.put(calculatedGid, tile);
                Log.writeInfo(tile.toString());
            }
        }
    }
    public boolean hasXmlTile(int GID) {

        return this.tilesWithProperties.containsKey(GID);

    }
    public XmlTile getXmlTileByGID(int GID) {


        return this.tilesWithProperties.get(GID);
    }




}
