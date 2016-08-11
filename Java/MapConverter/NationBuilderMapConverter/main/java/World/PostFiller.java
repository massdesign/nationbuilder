package World;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;

/**
 * Created by patrick on 11/2/14.
 */
public class PostFiller {

    private RubyContext context;
    private ClaimFiller claimFiller;
    private ConnectionFiller connectionFiller;
    private MapDataset mapDataset;
    public PostFiller(RubyContext context)
    {
        this.context = context;
        this.claimFiller = new ClaimFiller(context);
        this.connectionFiller  =  new ConnectionFiller(context);
    }

    public void Fill() throws ObjectConversionFailedException {

       // this.claimFiller.Fill();
        this.connectionFiller.Fill();
        this.save();

    }
   /* public void testFill() {


        Tile tile1 = this.context.createRubyModel(Tile.class);
        tile1.setXposition(20);
        tile1.setYposition(20);
        tile1.setXoffset(3);
        tile1.setYoffset(4);

        Tile tile2 = this.context.createRubyModel(Tile.class);
        tile2.setXposition(23);
        tile2.setYposition(43);
        tile2.setXoffset(9);
        tile2.setYoffset(15);

        Layer layer1  = this.context.createRubyModel(Layer.class);

        layer1.setName("Testlayer");
        layer1.setTileHeight(32);
        layer1.setTileWidth(44);
        layer1.setZindex(3);

        tile1.setLayer(layer1);
        tile2.setLayer(layer1);

        layer1.addTile(tile1);
        layer1.addTile(tile2);


        try
        {
            layer1.Save("layers");
            tile1.Save("tiles");
            tile2.Save("tiles");


            this.context.commit();
        }
        catch (RubyException e)
        {
            e.printStackTrace();
        }


    } */


     // NODE: powergrid components voorbeeld, niet weggooien totdat we powergrid components uit de xmlmap kunnen halen
    public void testFill() {


        Tile testTile1 = this.context.createRubyModel(Tile.class);
        Tile testTile2 = this.context.createRubyModel(Tile.class);
        Tile testTile3 = this.context.createRubyModel(Tile.class);
        TerrainType terrainType1 = this.context.createRubyModel(TerrainType.class);
        TerrainType terrainType2 = this.context.createRubyModel(TerrainType.class);
        Layer layer = this.context.createRubyModel(Layer.class);
        layer.setName("layer1");
        terrainType1.setName("Type1");
        terrainType2.setName("Type2");


        testTile1.setXoffset(0);
        testTile1.setYoffset(0);

        testTile2.setXoffset(1);
        testTile2.setYoffset(1);

        testTile3.setXoffset(2);
        testTile3.setYoffset(2);

        layer.addTile(testTile1);
        layer.addTile(testTile2);
        layer.addTile(testTile3);

        testTile1.setTerrainType(terrainType1);
        testTile2.setTerrainType(terrainType2);
        testTile3.setTerrainType(terrainType1);

        try
        {
            layer.Save();

            this.context.commit();
        }
        catch (RubyException e)
        {
            e.printStackTrace();
        }




      /*  MapMap map = this.context.createRubyModel(MapMap.class);
        map.setHeight(39);
        map.setWidth(40);
        map.setTileHeight(30);
        map.setTileWidth(30);


        Layer layer = this.context.createRubyModel(Layer.class);

        layer.setName("lekker testen");
        layer.setTileHeight(4);
        layer.setZindex(43);
        layer.setTileHeight(30);
        layer.setTileWidth(40);

        map.addLayer(layer);

        Tile tile = this.context.createRubyModel(Tile.class);

        tile.setXoffset(3);
        tile.setYoffset(45);
        tile.setXposition(32);
        tile.setYposition(44);
        layer.addTile(tile);
        */


        /*try
        {
            map.Save();
            this.context.commit();

        }
        catch (RubyException e)
        {
            e.printStackTrace();
        }*/




       /* PowerRelayStation powerRelayStation1 = context.createRubyModel(PowerRelayStation.class);
        PowerRelayStation powerRelayStation2 = context.createRubyModel(PowerRelayStation.class);

        powerRelayStation1.setName("PRS01");
        powerRelayStation2.setName("PRS02");

        PowerGridNode powerGridNode1 = context.createRubyModel(PowerGridNode.class);
        PowerGridNode powerGridNode2 = context.createRubyModel(PowerGridNode.class);

        PowerGridNode powerGridNode3 = context.createRubyModel(PowerGridNode.class);

        WareHouse wareHouse = context.createRubyModel(WareHouse.class);
        wareHouse.setName("even testen hoor");



        PowerRelayStation powerRelayStation3 = context.createRubyModel(PowerRelayStation.class);

        powerRelayStation3.setName("testje voor je moeder");


        powerGridNode1.setName("Lakeside power grid node");
        powerGridNode1.setDestroyable(true);
        powerGridNode2.setName("Countryside power grid node");

        PowerRelayStationType powerRelayStationType = context.createRubyModel(PowerRelayStationType.class);
        powerRelayStationType.setName("Dynatec Standard 300MW power distribution station");
        powerRelayStationType.setCapacity(300);

        PowerConnection powerConnection = context.createRubyModel(PowerConnection.class);

        powerConnection.setA(powerGridNode1);
        powerConnection.setB(powerGridNode2);

        powerConnection.setName("Test connection");
        powerConnection.setLoad(21);
        powerConnection.setCapacity(300);


        powerRelayStation1.setPowerRelayStationType(powerRelayStationType);
        powerRelayStation2.setPowerRelayStationType(powerRelayStationType);
        powerGridNode1.setRelayStation(powerRelayStation1);
        powerGridNode2.setRelayStation(powerRelayStation2);

        try
        {
            powerGridNode3.setName("test in je moeder");
            powerGridNode3.setDestroyable(true);
         //   wareHouse.Save("warehouses");
         /*   powerRelayStation3.Save("buildings");
            powerGridNode3.Save("power_grid_nodes");

            powerRelayStationType.Save("power_relay_station_types");
            powerRelayStation1.Save("power_relay_stations");
            powerRelayStation2.Save("power_relay_stations");
            powerGridNode1.Save("power_grid_nodes");
            powerGridNode2.Save("power_grid_nodes");
            powerConnection.Save("power_connections");


            this.context.commit();
        }
        catch (RubyException e)
        {
            e.printStackTrace();
        } */
    }
    private void save()
    {
        // uitgezet voor testen
       // this.claimFiller.Save(Claim.class,"/claims/");
        //  TODO: ruby controllers hiervoor maken
        this.connectionFiller.Save(Node.class,"/node_types/");
        this.connectionFiller.Save(PowerRelayStation.class,"");
        //this.connectionFiller.Save(ConnectionType_old.class,"");
        this.connectionFiller.Save(PowerConnection.class,"");

    }

    public MapDataset getMapDataset() {
        return mapDataset;
    }

    public void setMapDataset(MapDataset mapDataset) {
        this.mapDataset = mapDataset;
        this.claimFiller.setMapDataset(mapDataset);
    }


}
