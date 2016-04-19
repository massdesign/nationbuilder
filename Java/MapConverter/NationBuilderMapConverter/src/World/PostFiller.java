package World;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.PowerConnection;
import nationbuilder.lib.data.map.entities.ConnectionType;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.entities.Node;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.entities.PowerRelayStationType;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.entities.WareHouse;

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

    public void testFill() {

        PowerRelayStation powerRelayStation1 = context.createRubyModel(PowerRelayStation.class);
        PowerRelayStation powerRelayStation2 = context.createRubyModel(PowerRelayStation.class);

        Tile tile1 = context.createRubyModel(Tile.class);
        Tile tile2 = context.createRubyModel(Tile.class);

        tile1.setXoffset(0);
        tile1.setYoffset(0);
        tile1.setXposition(2);
        tile1.setYposition(2);


        powerRelayStation1.setName("PRS01");
        powerRelayStation2.setName("PRS02");


        powerRelayStation1.setLocatedOn(tile1);
        powerRelayStation1.setLocatedOn(tile2);


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
            tile1.Save("tiles");
            tile2.Save("tiles");
            powerGridNode3.setName("test in je moeder");
            powerGridNode3.setDestroyable(true);
         //   wareHouse.Save("warehouses");
            //powerRelayStation3.Save("buildings");
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
        }
    }
    private void save()
    {
        // uitgezet voor testen
       // this.claimFiller.Save(Claim.class,"/claims/");
        this.connectionFiller.Save(PowerRelayStationType.class, "power_relay_station_types");
        this.connectionFiller.Save(PowerRelayStation.class, "power_relay_stations");
        this.connectionFiller.Save(PowerGridNode.class,"power_grid_nodes");
        this.connectionFiller.Save(PowerConnection.class, "power_connections");
        this.connectionFiller.Save(Node.class,"node_types");


    }

    public MapDataset getMapDataset() {
        return mapDataset;
    }

    public void setMapDataset(MapDataset mapDataset) {
        this.mapDataset = mapDataset;
        this.claimFiller.setMapDataset(mapDataset);
        this.connectionFiller.setMapDataset(mapDataset);
    }


}
