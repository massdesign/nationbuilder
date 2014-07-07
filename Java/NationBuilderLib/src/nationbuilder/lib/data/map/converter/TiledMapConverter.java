package nationbuilder.lib.data.map.converter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.xml.Image;
import nationbuilder.lib.data.map.xml.Layer;
import nationbuilder.lib.data.map.xml.Tile;
import nationbuilder.lib.data.map.xml.TileSet;
import nationbuilder.lib.data.map.xml.TiledXmlMap;

public class TiledMapConverter {

    TiledXmlMap xmlMap;
    ArrayList<MapImage> mapImages;
    ArrayList<MapTile> mapTiles;
    MapMap map;
    HashMap<String,MapLayer> mapLayers;
    RubyContext rubyContext = new RubyContext();
    public TiledMapConverter(TiledXmlMap xmlMap)
    {
        this();
        this.xmlMap = xmlMap;
    }
    public TiledMapConverter()
    {
        this.mapLayers = new HashMap<String,MapLayer>();
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
    private ArrayList<MapImage> convertTilesets(ArrayList<TileSet> tilesets)
    {
        ArrayList<MapImage> mapImages = new ArrayList<MapImage>();
        for(TileSet tileset : tilesets)
        {
            Image image =	tileset.getImage();

            MapImage mapImage = new MapImage();
           // MapImage mapImage = this.rubyContext.createRubyModel(MapImage);
            mapImage.setMap(this.map);
            mapImage.setImageFile(new File(image.getFileLocation()));
            mapImage.setHeight(image.getHeight());
            mapImage.setWidth(image.getWidth());

            mapImage.setTileHeight(tileset.getTileHeight());
            mapImage.setTileWidth(tileset.getTileWidth());
            mapImage.setFirstGid(tileset.getFirstGid());
            mapImage.setLastGid(tileset.getLastGid());

            mapImage.setUrl("/upload/" + image.getName());
            mapImage.setName(image.getName());
            int status_code = 0;

            mapImages.add(mapImage);
        }

        return mapImages;

    }

    public MapTile convertTile(Tile tile)
    {
        MapTile result = new MapTile();
        result.setGidtag(tile.getGID());

        if(this.mapTileSetImage(result, tile.getGID()))
        {
            if(!this.mapTileImageOffset(result,tile.getGID()))
            {
                result = null;
            }
        }
        else
        {
            result = null;
        }

        return result;
    }
    private boolean mapTileImageOffset(MapTile newTile,int tile_gid)
    {

        boolean result = true;
        int offset = newTile.getImage().getFirstGid();
        int columns = newTile.getImage().getWidth()/newTile.getImage().getTileWidth();
        int rows = newTile.getImage().getHeight()/newTile.getImage().getTileHeight();
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
        return result;

    }
    private boolean mapTileSetImage(MapTile newtile,int gid)
    {
        boolean result = false;
        for(int x=0;x<mapImages.size();x++)
        {


            if(gid >= this.mapImages.get(x).getFirstGid())
            {
                if((x+1) != mapImages.size())
                {
                    int lastGid = this.mapImages.get(x+1).getFirstGid();
                    // do minus -1 because otherwise we select the first element of the next tileset
                    if(gid <= (lastGid-1))
                    {
                        newtile.setImage(this.mapImages.get(x));
                        result = true;
                        break;
                    }
                }
                else
                {
                    newtile.setImage(this.mapImages.get(x));
                    result = true;
                    break;
                }
            }
        }
        return result;
    }

    public ArrayList<MapTile> convertLayer(ArrayList<Layer> layers)
    {
        ArrayList<MapTile> result = new ArrayList<MapTile>();
        int zindex = 0;
        try {
            for(Layer layer : layers)
            {
                ArrayList<Tile> tiles = layer.getTiles();
                int tilepositionx = 0;
                int tilepositiony = 0;

                for(Tile tile : tiles)
                {
                    if(tile.getGID() != 0)
                    {

                        MapTile newTile = this.convertTile(tile);

                        if(this.mapLayers.containsKey(layer.getName()))
                        {

                            String layerName = layer.getName();
                            newTile.setLayer(this.mapLayers.get(layerName));

                        }
                        else
                        {
                            MapLayer newLayer = new MapLayer();
                            newLayer.setZindex(zindex);
                            newLayer.setMap(this.map);
                            newLayer.setName(layer.getName());
                            newLayer.setTileHeight(layer.getHeight());
                            newLayer.setTileWidth(layer.getWidth());
                            this.mapLayers.put(layer.getName(),newLayer);
                            newTile.setLayer(newLayer);

                        }

                        // give the tile a meaningfull position
                        newTile.setXposition(tilepositionx);
                        newTile.setYposition(tilepositiony);
                        result.add(newTile);
                        // -1 omdat we zerobased index gebruiken

                    }
                    if(tilepositionx == layer.getWidth()-1)
                    {
                        tilepositionx = 0;
                        tilepositiony++;
                    }
                    else
                    {
                        tilepositionx++;
                    }
                }

                zindex++;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
            // log error and tell
        }
        return result;
    }
    public MapDataset GetMapDataset()
    {
        MapDataset result = new MapDataset();
        result.setMap(this.map);
        result.setMapImages(this.mapImages);
        result.setMapTiles(this.mapTiles);
        result.setMapLayers(mapLayers);
        return result;
    }
    public void Convert()
    {
        this.map = this.convertMap(this.xmlMap);
        this.mapImages = this.convertTilesets(this.xmlMap.getTilesets());
        this.mapTiles =  this.convertLayer(this.xmlMap.getLayers());
    }

}
