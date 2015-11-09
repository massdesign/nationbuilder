package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.IgnoreInRails;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * Created by patrick on 11/2/14.
 */
@Entity(tableName = "claims")
public class Claim extends BaseRubyModel {

    private String tile_id;
    private String state_id;

    @IgnoreInRails
    @OneToOne(mapIdTo = "state_id")
    private GameEntity claimedBy;
    @IgnoreInRails
    @OneToOne(mapIdTo = "tile_id")
    private Tile claimedTile;

    public Tile getClaimedTile() {
        return claimedTile;
    }

    public void setClaimedTile(Tile claimedTile) {
        this.claimedTile = claimedTile;
    }

    public GameEntity getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(GameEntity claimedBy) {
        this.claimedBy = claimedBy;
    }


}

