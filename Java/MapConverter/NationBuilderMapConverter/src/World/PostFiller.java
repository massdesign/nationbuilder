package World;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Claim;
import nationbuilder.lib.data.map.entities.MapDataset;

/**
 * Created by patrick on 11/2/14.
 */
public class PostFiller {

    private RubyContext context;
    private ClaimFiller claimFiller;
    ;
    private MapDataset mapDataset;
    public PostFiller(RubyContext context)
    {
        this.context = context;
        this.claimFiller = new ClaimFiller(context);
    }

    public void Fill()
    {
        // TODO: refactor these models also in the new BaseFiller model


        this.claimFiller.Fill();
        this.save();

    }
    private void save()
    {
        this.claimFiller.Save(Claim.class,"/claims/");

    }

    public MapDataset getMapDataset() {
        return mapDataset;
    }

    public void setMapDataset(MapDataset mapDataset) {
        this.mapDataset = mapDataset;
        this.claimFiller.setMapDataset(mapDataset);
    }


}
