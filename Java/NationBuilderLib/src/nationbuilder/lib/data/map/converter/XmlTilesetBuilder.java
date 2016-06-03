package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.data.map.converter.converterobjects.ProcessedMap;
import nationbuilder.lib.data.map.converter.converterobjects.ProcessedTileDefinition;
import nationbuilder.lib.data.map.converter.converterobjects.ProcessedTileset;
import nationbuilder.lib.data.map.xml.XmlTileset;

import java.util.ArrayList;
import java.util.HashMap;
import nationbuilder.lib.data.map.xml.XmlTileDefinition;

/**
 * Created by patrick on 5/16/16.
 */
public class XmlTilesetBuilder
{
    private XmlImageBuilder imageBuilder;
    private HashMap<Integer,XmlTileDefinition> tilesWithProperties;
   // private XmlTileBuilder tileBuilder;
    // TODO: waarschijnlijk is dit overbodig
    private ConverterContext converterContext;


    public XmlTilesetBuilder(ConverterContext converterContext) {
        this.tilesWithProperties = new HashMap<>();
        this.converterContext = converterContext;
        this.imageBuilder = new XmlImageBuilder(this.converterContext);
    }

    public void build(XmlTileset tileSet,ProcessedMap processedMap)
    {
        // TODO: besluiten of we een builder maken voor de tiles definitions of dat we dit opslaan in een service..
        ArrayList<XmlTileDefinition> tiles = tileSet.getTilesDefinitions();
        ProcessedTileset processedTileset = new ProcessedTileset();

        processedTileset.setFirstgid(tileSet.getFirstGid());
        processedTileset.setName(tileSet.getName());
        processedTileset.setTileheight(tileSet.getTileHeight());
        processedTileset.setTilewidth(tileSet.getTileWidth());
        XmlImageBuilder xmlImageBuilder = new XmlImageBuilder(this.converterContext);
        xmlImageBuilder.build(tileSet,processedTileset);

        for(XmlTileDefinition tile : tiles)
        {
            // TODO: dit verplaatsen op de plek waar de tiles geconstrueerd worden
            int calculatedGid =  tileSet.getFirstGid()+tile.getId();
            if(!this.tilesWithProperties.containsKey(calculatedGid))
            {
                ProcessedTileDefinition newDefinition = new ProcessedTileDefinition();
                newDefinition.setId(tile.getId());
                processedTileset.addProcessedTileDefinition(newDefinition);

                XmlPropertyBuilder propertyBuilder = new XmlPropertyBuilder(this.converterContext);
                propertyBuilder.build(tile,newDefinition);
                processedTileset.addProcessedTileDefinition(newDefinition);

               // this.converterContext.getMap()
                this.tilesWithProperties.put(calculatedGid, tile);
                Log.writeInfo(tile.toString());
               // this.tileBuilder.build(tile);
            }
        }

        processedMap.add(processedTileset);
    }
    public boolean hasXmlTile(int GID) {

        return this.tilesWithProperties.containsKey(GID);

    }
    public XmlTileDefinition getXmlTileByGID(int GID) {


        return this.tilesWithProperties.get(GID);
    }




}
