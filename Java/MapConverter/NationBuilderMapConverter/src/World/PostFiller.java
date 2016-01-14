package World;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Claim;
import nationbuilder.lib.data.map.entities.Connection;
import nationbuilder.lib.data.map.entities.ConnectionType;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.entities.NodeType;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;

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


        PowerGridNode type = context.createRubyModel(PowerGridNode.class);
        type.setName("test node types");
        type.setDestroyable(true);
        try
        {
         //   type.Save("/node_types/");
            type.Save("/power_grid_nodes/");
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
        //  TODO: ruby controllers hiervoor maken
        this.connectionFiller.Save(NodeType.class,"/node_types/");
        this.connectionFiller.Save(PowerRelayStation.class,"");
        this.connectionFiller.Save(ConnectionType.class,"");
        this.connectionFiller.Save(Connection.class,"");

    }

    public MapDataset getMapDataset() {
        return mapDataset;
    }

    public void setMapDataset(MapDataset mapDataset) {
        this.mapDataset = mapDataset;
        this.claimFiller.setMapDataset(mapDataset);
    }


}
