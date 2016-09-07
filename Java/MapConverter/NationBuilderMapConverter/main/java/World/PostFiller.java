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

    public void testFill() {


        TestFiller testFiller = new TestFiller(this.context);
        testFiller.testFillSmallNationbuilderDatastructure();

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
