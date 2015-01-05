package nationbuilder.lib.data.map.converter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.xml.*;

public class TiledMapConverter {

    TiledXmlMap xmlMap;
    ArrayList<Image> mapImages;
    ArrayList<Tile> mapTiles;
    ArrayList<Resource> resources;
    MapMap map;
    HashMap<String,Layer> mapLayers;
    RubyContext rubyContext;
    private HashMap<Integer,XmlTile> tilesWithterrainTypes;


    public TiledMapConverter(TiledXmlMap xmlMap,RubyContext context)
    {
        this();
        this.xmlMap = xmlMap;
        this.rubyContext = context;
    }
    public TiledMapConverter()
    {
        this.mapLayers = new HashMap<String,Layer>();
        this.tilesWithterrainTypes = new HashMap<Integer, XmlTile>();
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
    private void addtilesToterrainTypeS(TileSet tileSet)
    {
        ArrayList<XmlTile> tiles = tileSet.getTiles();


        // TODO: deze manier van id's mappen is fout, in Tiled tiles zijn alleen uniek binnen tileset
        for(XmlTile tile : tiles)
        {
            int calculatedGid =  tileSet.getFirstGid()+tile.getGID();
            if(!this.tilesWithterrainTypes.containsKey(calculatedGid))
            {
                this.tilesWithterrainTypes.put(calculatedGid,tile);
                Log.writeInfo(tile.toString());
            }
        }

      //  System.out.println("some random crap to put a breakpoint on");
    }
    private ArrayList<Image> convertTilesets(ArrayList<TileSet> tilesets)
    {
        ArrayList<Image> mapImages = new ArrayList<Image>();
        for(TileSet tileset : tilesets)
        {
            addtilesToterrainTypeS(tileset);
            nationbuilder.lib.data.map.xml.Image image =	tileset.getImage();

            Image mapImage = rubyContext.createRubyModel(Image.class);
            MapImageFile mapImageFile = rubyContext.createRubyModel(MapImageFile.class);
            mapImage.setMap(this.map);
            mapImageFile.setResource(new File(image.getFileLocation()));
            mapImage.setImageFile(mapImageFile);
            mapImage.setHeight(image.getHeight());
            mapImage.setWidth(image.getWidth());

            mapImage.setTileHeight(tileset.getTileHeight());
            mapImage.setTileWidth(tileset.getTileWidth());
            mapImage.setFirstGid(tileset.getFirstGid());
            mapImage.setLastGid(tileset.getLastGid());

            mapImage.setUrl("/upload/" + image.getName());
            mapImage.setName(image.getName());

            mapImages.add(mapImage);
        }

        return mapImages;

    }
    private ArrayList<Resource> convertTilesetPropertiesToResources()
    {
        ArrayList<Resource> resources = new ArrayList<Resource>();


        return resources;
    }

    public Tile convertTile(XmlTile tile)
    {
        Tile result = this.rubyContext.createRubyModel(Tile.class);
        result.setGidtag(tile.getGID());
         // TODO: volgens mij kan deze code ook wel een stukje korter.. maak unit test voor dit ding en refactor hem dan
        if(this.mapTileSetImage(result, tile.getGID()))
        {

            if(!this.mapTileImageOffset(result,tile.getGID()))
            {
                result = null;
            }
            if(this.tilesWithterrainTypes.containsKey(tile.getGID()))
            {
                Resource resource =  this.rubyContext.createRubyModel(Resource.class);
                resource.setTerrainType(convertPropertyToTerrainType(this.tilesWithterrainTypes.get(tile.getGID()).getProperties()));
                resource.addResourceType(this.getResourceType(0));
                resource.addResourceType(this.getResourceType(1));
                result.setResources(resource);
                // NOTE: dit is een beetje lelijk nu wordt er een lijstje op een aparte manier dat later opgeslagen wordt..
                this.resources.add(resource);
            }
        }
        else
        {
            result = null;
        }

        return result;
    }

    /**
     * Methode om stukjes code in te testen
     */
    public void runSampleCode()
    {

    }
    private TerrainType convertPropertyToTerrainType(ArrayList<Property> properties)
    {
        TerrainType result = null;
        // search in the database for the right TerrainType..

        // TODO: pretty ineffecient way of quering the db, find a way to handle db queries in a generic way
        // SQL syntax for this bitch would be SELECT id from resourcetype WHERE name = 'name'
        for(Property property : properties)
        {
            if(property.getName().toLowerCase().equals("tiletype"))
            {
                List<TerrainType> models = this.rubyContext.getModels(TerrainType.class);
                for(TerrainType model : models)
                {
                    if(model.getName().equals(property.getValue()))
                    {
                        result = model;
                        break;
                    }
                }
            }
        }
        return result;
    }
    private ResourceType getResourceType(int index)
    {
        // pick the first one.. does nog matter.. it is only for coupling.. resources will be designated in the xml.. later
       List<ResourceType> result = this.rubyContext.getModels(ResourceType.class);
       return result.get(index);
    }
    private boolean mapTileImageOffset(Tile newTile,int tile_gid)
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
    private boolean mapTileSetImage(Tile newtile,int gid)
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

    public ArrayList<Tile> convertLayer(ArrayList<XmlLayer> layers)
    {
        ArrayList<Tile> result = new ArrayList<Tile>();
        int zindex = 0;
        try {
            for(XmlLayer layer : layers)
            {
                ArrayList<XmlTile> tiles = layer.getTiles();
                int tilepositionx = 0;
                int tilepositiony = 0;

                for(XmlTile tile : tiles)
                {
                    if(tile.getGID() != 0)
                    {

                        Tile newTile = this.convertTile(tile);

                        if(this.mapLayers.containsKey(layer.getName()))
                        {

                            String layerName = layer.getName();
                            newTile.setLayer(this.mapLayers.get(layerName));

                        }
                        else
                        {
                            Layer newLayer = this.rubyContext.createRubyModel(Layer.class);
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
        result.setResources(resources);
        result.setMapImages(this.mapImages);
        result.setMapTiles(this.mapTiles);
        result.setMapLayers(mapLayers);
        return result;
    }
    public void Convert()
    {
        this.map = this.convertMap(this.xmlMap);
        this.resources = this.convertTilesetPropertiesToResources();
        this.mapImages = this.convertTilesets(this.xmlMap.getTilesets());
        this.mapTiles =  this.convertLayer(this.xmlMap.getLayers());


    }

}
