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

        powerRelayStation1.setName("PRS01");
        powerRelayStation2.setName("PRS02");

        PowerGridNode powerGridNode1 = context.createRubyModel(PowerGridNode.class);
        PowerGridNode powerGridNode2 = context.createRubyModel(PowerGridNode.class);

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
            powerRelayStationType.Save("/power_relay_station_types");
            powerRelayStation1.Save("/power_relay_stations");
            powerRelayStation2.Save("/power_relay_stations");
            powerGridNode1.Save("/power_grid_nodes/");
            powerGridNode2.Save("/power_grid_nodes/");
            powerConnection.Save("/power_connections/");
            this.context.commit();
        }
        catch (RubyException e)
        {
            e.printStackTrace();
        }

        //try
        //{
         //   type.setRelayStation(powerRelayStation);


        //}
        //catch (RubyException e)
        //{
         //   e.printStackTrace();
       // }



    }
    private void save()
    {
        // uitgezet voor testen
       // this.claimFiller.Save(Claim.class,"/claims/");
        //  TODO: ruby controllers hiervoor maken
        this.connectionFiller.Save(Node.class,"/node_types/");
        this.connectionFiller.Save(PowerRelayStation.class,"");
        this.connectionFiller.Save(ConnectionType.class,"");
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
