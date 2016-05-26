package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.Ruby.Exceptions.NoImageForTileException;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.xml.Property;
import nationbuilder.lib.data.map.xml.TileSet;
import nationbuilder.lib.data.map.xml.XmlTile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 5/15/16.
 */
public class XmlTileBuilder {

    private TilesetBuilder tilesetManager;
    private ImageBuilder imageManager;
    private TerrainTypeBuilder terrainTypeBuilder;
    public XmlTileBuilder(ImageBuilder imageBuilder) {
           this.tilesetManager = new TilesetBuilder();
           this.imageManager = new ImageBuilder(this);
           this.terrainTypeBuilder = new TerrainTypeBuilder(this);
    }

    public void addTileset(TileSet tileSet) {
        this.tilesetManager.addTileset(tileSet);
    }

    public boolean isTile(XmlTile tile) {

        if(tile.getProperties().size() > 0) {


            return hasProperty("tiletype", tile);
        }
        else {
            return true;
        }

    }
    public boolean isCityItem(XmlTile tile) {

        return hasProperty("city_type",tile);
    }

    public boolean isPowerGridItem(XmlTile tile) {

        return hasProperty("powerstation_type",tile);
    }




    private void mapTileImageOffset(Tile newTile,int tile_gid)
    {

        // boolean result = true;
        int offset = newTile.getImage().getFirstGid();
        int columns = newTile.getImage().getWidth()/newTile.getImage().getTileWidth();
        // int rows = newTile.getImage().getHeight()/newTile.getImage().getTileHeight();
        int gid_counter = offset;
        // first
        int currentrow = 1;
        int currentcolumn = 1;


        while(gid_counter < (tile_gid))
        {
            gid_counter++;
            if(currentcolumn == columns)
            {
                currentcolumn = 1;
                currentrow++;
            }
            else
            {
                currentcolumn++;
            }

        }
        newTile.setXoffset(currentcolumn-1);
        newTile.setYoffset(currentrow-1);

    }

    public Tile convertTile(XmlTile tile,Tile result,List<TerrainType> terrainTypes) throws NoImageForTileException {
       // Tile result = this.rubyContext.createRubyModel(Tile.class);
        result.setGidtag(tile.getGID());
        result.setImage(this.imageManager.getTileImage(tile.getGID()));
       // this.setTileImage(result, tile.getGID());
        // if setTileImage does not manage to find a suitable image for this than we throw an exception
        if(result.getImage() != null) {

            this.mapTileImageOffset(result, tile.getGID());
            if (this.tilesetManager.hasXmlTile(tile.getGID())) {

                this.terrainTypeBuilder.convertPropertyToTerrainType(this.tilesetManager.getXmlTileByGID(tile.getGID()).getProperties(),terrainTypes);
            }
        }
        else {
            throw new NoImageForTileException("Tile with ID" + tile.getGID() + " has no suitable image associated with it");
        }

        return result;
    }



    private boolean hasProperty(String propertyName ,XmlTile tile) {
        boolean result = false;
        for(Property xmlProperty : tile.getProperties()) {

            if(xmlProperty.getName().toLowerCase().equals(propertyName)) {

                result = true;
                break;
            }

        }
        return result;
    }


}
