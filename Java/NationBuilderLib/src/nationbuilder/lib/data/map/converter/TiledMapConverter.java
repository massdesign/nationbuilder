package nationbuilder.lib.data.map.converter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.xml.*;

public class TiledMapConverter {

    TiledXmlMap xmlMap;
    ArrayList<Image> mapImages;
   // ArrayList<Tile> mapTiles;
    ArrayList<Resource> resources;
    MapMap map;
    //HashMap<String,Layer> mapLayers;
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
        //this.tilesWithterrainTypes = new HashMap<Integer, XmlTile>();
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
    /*private void addtilesToterrainTypeS(XmlTileSet tileSet)
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
    }*/
    private ArrayList<Image> convertTilesets(ArrayList<XmlTileSet> tilesets)
    {
        ArrayList<Image> mapImages = new ArrayList<Image>();
        for(XmlTileSet tileset : tilesets)
        {
            //addtilesToterrainTypeS(tileset);
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
    /*
    private ResourceType getResourceType(int index)
    {
        // pick the first one.. does nog matter.. it is only for coupling.. resources will be designated in the xml.. later
       List<ResourceType> result = this.rubyContext.getModels(ResourceType.class);
       return result.get(index);
    }*/



    public ArrayList<Tile> convertLayer(ArrayList<XmlLayer> layers)
    {
       // ArrayList<Tile> result = new ArrayList<Tile>();
    //    int zindex = 0;
      //  try
        //{
            LayerBuilder layerBuilder = new LayerBuilder(this.rubyContext,this.map,this.mapImages);

            for (XmlLayer layer : layers)
            {

                this.layers.add(layerBuilder.createLayer(layer));

            }
               // ArrayList<XmlTile> tiles = layer.getTiles();
            //    int tilepositionx = 0;
              //  int tilepositiony = 0;
               // for (XmlTile tile : tiles)
             //   {
                   // if (tile.getGID() != 0)
                   // {
                       // Layer currentLayer = null;
                       // if (this.mapLayers.containsKey(layer.getName()))
                       // {

                          //  String layerName = layer.getName();
                          //  currentLayer = this.mapLayers.get(layerName);
                            //newTile.setLayer(this.mapLayers.get(layerName));
                      //  }
                      //  else
                      //  {
                            /*Layer newLayer = this.rubyContext.createRubyModel(Layer.class);
                            newLayer.setZindex(zindex);
                            newLayer.setMap(this.map);
                            newLayer.setName(layer.getName());
                            newLayer.setTileHeight(layer.getHeight());
                            newLayer.setTileWidth(layer.getWidth());
                            this.mapLayers.put(layer.getName(), newLayer);
                            currentLayer = newLayer;
                            //newTile.setLayer(newLayer);
                            */

                      //  }
                        //Tile newTile = this.createTile(tile, currentLayer,tilepositionx,tilepositiony);
                        // give the tile a meaningfull position
                       // newTile.setXposition(tilepositionx);
                        //newTile.setYposition(tilepositiony);
                      //  result.add(newTile);
                        // -1 omdat we zerobased index gebruiken

                   // }
                 //   if (tilepositionx == layer.getWidth() - 1)
                 //   {
                 //       tilepositionx = 0;
                 //       tilepositiony++;
                   // }
                   // else
                   // {
                  //      tilepositionx++;
                  //  }
                return layerBuilder.getTiles();
                }

                //zindex++;
         /*   }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            // log error and tell
        }
        return result;
        */
    //}

    /*private Layer createLayer(XmlLayer layer,int zindex) {
        Layer newLayer = this.rubyContext.createRubyModel(Layer.class);
        newLayer.setZindex(zindex);
        newLayer.setMap(this.map);
        newLayer.setName(layer.getName());
        newLayer.setTileHeight(layer.getHeight());
        newLayer.setTileWidth(layer.getWidth());

       // this.mapLayers.put(layer.getName(), newLayer);
        this.layers.add(newLayer);
        return newLayer;
    }*/




    private Tile createTile(XmlTile tile,Layer currentLayer,int tilepositionx,int tilepositiony) {

        TileBuilder tileBuilder = new TileBuilder(currentLayer,this.rubyContext,this.mapImages);

        return tileBuilder.createTile(tilepositionx,tilepositiony,tile);
        //Tile newTile = this.convertTile(tile);
        //newTile.setLayer(currentLayer);

        // give the tile a meaningfull position
        //newTile.setXposition(tilepositionx);
        //newTile.setYposition(tilepositiony);
        //return newTile;
    }

    public MapDataset GetMapDataset()
    {
        MapDataset result = new MapDataset();
        result.setMap(this.map);
      //  result.setResources(resources);
        result.setMapImages(this.mapImages);
        result.setMapTiles(this.mapTiles);
        result.setMapLayers(this.layers);
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
