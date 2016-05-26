package nationbuilder.lib.data.map.converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nationbuilder.lib.Ruby.Exceptions.NoImageForTileException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.xml.*;

public class TiledMapConverter {

    TiledXmlMap xmlMap;
   // ArrayList<Image> mapImages;
    ArrayList<Tile> mapTiles;
    ArrayList<Resource> resources;
    MapMap map;
    HashMap<String,Layer> mapLayers;
    RubyContext rubyContext;
    XmlTileBuilder xmlTileBuilder;


    public TiledMapConverter(TiledXmlMap xmlXmlTileFactoryMap,RubyContext context)
    {
        this();

        xmlTileBuilder =new XmlTileBuilder();
        this.rubyContext = context;
    }
    public TiledMapConverter()
    {
        this.mapLayers = new HashMap<String,Layer>();
       // this.tilesWithterrainTypes = new HashMap<Integer, XmlTile>();
    }

    // TODO: deze methode aan een andere klasse toevoegen
   /* private void addTilesToTerrainTypes(TileSet tileSet)
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

    /*
    private ResourceType getResourceType(int index)
    {
        // pick the first one.. does nog matter.. it is only for coupling.. resources will be designated in the xml.. later
       List<ResourceType> result = this.rubyContext.getModels(ResourceType.class);
       return result.get(index);
    }*/


    // TODO: we hebben meer nodig dan een lijst van tiles.. een composiet datatype dat ook meer kan bevatten dan tiles
    public ArrayList<Tile> convertLayer(ArrayList<XmlLayer> layers)
    {
        XmlTileBuilder xmlTileFactory = new XmlTileBuilder();
        ArrayList<Tile> tilesResult = new ArrayList<>();
        ArrayList<Tile> citiesResult = new ArrayList<>();
        ArrayList<Tile> powergridResult = new ArrayList<>();


        ArrayList<XmlTile> items= new ArrayList<>();

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
                        Layer currentLayer;
                        if (this.mapLayers.containsKey(layer.getName())) {

                            String layerName = layer.getName();
                            currentLayer = this.mapLayers.get(layerName);

                            //newTile.setLayer(this.mapLayers.get(layerName));

                        } else {
                           /* Layer newLayer = this.rubyContext.createRubyModel(Layer.class);
                            newLayer.setZindex(zindex);
                            newLayer.setMap(this.map);
                            newLayer.setName(layer.getName());
                            newLayer.setTileHeight(layer.getHeight());
                            newLayer.setTileWidth(layer.getWidth());
                            this.mapLayers.put(layer.getName(), newLayer);
                            //newTile.setLayer(newLayer);*/

                            currentLayer = createLayer(layer,zindex);

                        }

                        if(layer.getName().equals("items")) {
                            items.add(tile);
                        }

                        if(xmlTileFactory.isPowerGridItem(tile)) {

                            powergridResult.add(this.createTile(tile,currentLayer,tilepositionx,tilepositiony));

                         }
                        // normal tiles aan de normale lijst toevoegen
                        else if(xmlTileFactory.isTile(tile)) {
                            // we maken een onderscheidt tussen tiles en mapitems
                              tilesResult.add(this.createTile(tile, currentLayer, tilepositionx, tilepositiony));
                        }
                        // city tiles aan de city tile lijst toevoegen
                        else  if(xmlTileFactory.isCityItem(tile)) {
                            // NOTE: duplicate code
                                 citiesResult.add(this.createTile(tile, currentLayer, tilepositionx, tilepositiony));

                        }
                        // power grid items toevoegen aan de powergridResult

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
        return tilesResult;
    }

    private Layer createLayer(XmlLayer layer,int zindex) {
        Layer newLayer = this.rubyContext.createRubyModel(Layer.class);
        newLayer.setZindex(zindex);
        newLayer.setMap(this.map);
        newLayer.setName(layer.getName());
        newLayer.setTileHeight(layer.getHeight());
        newLayer.setTileWidth(layer.getWidth());
        this.mapLayers.put(layer.getName(), newLayer);
        return newLayer;
    }

    private Tile createTile(XmlTile tile,Layer currentLayer,int tilepositionx,int tilepositiony) throws NoImageForTileException {
        List<TerrainType> terrainTypeList =  this.rubyContext.getModels(TerrainType.class);
        Tile newTile = this.xmlTileBuilder.convertTile(tile,(Tile)this.rubyContext.createRubyModel(Tile.class),terrainTypeList);
        newTile.setLayer(currentLayer);

        // give the tile a meaningfull position
        newTile.setXposition(tilepositionx);
        newTile.setYposition(tilepositiony);
        return newTile;
    }

    public MapDataset GetMapDataset()
    {
        MapDataset result = new MapDataset();
        result.setMap(this.map);
      //  result.setResources(resources);
        //result.setMapImages(this.mapImages);
        result.setMapTiles(this.mapTiles);
        result.setMapLayers(mapLayers);
        return result;
    }
    public void Convert()
    {
        XmlTileBuilderFactory xmlTileBuilderFactory = new XmlTileBuilderFactory(this.xmlMap.getTilesets());

        TilesetBuilder tilesetBuilder = new TilesetBuilder();
        ImageBuilder imageBuilder = new ImageBuilder(tilesetBuilder);
        XmlTileBuilder xmlTileBuilder =  xmlTileBuilderFactory.createXmlTileBuilder();
        MapBuilder mapBuilder = new MapBuilder(tilesetBuilder,xmlTileBuilder);

        this.map =   mapBuilder.convertMap(this.xmlMap);
        //this.map = this.convertMap(this.xmlMap);
        this.resources = this.convertTilesetPropertiesToResources();

       //xmlTileBuilder.addTileset();

       // this.mapImages = this.convertTilesets(this.xmlMap.getTilesets());
        this.mapTiles =  this.convertLayer(this.xmlMap.getLayers());


    }

}
