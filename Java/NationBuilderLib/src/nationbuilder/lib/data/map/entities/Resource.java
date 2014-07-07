package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.http.data.ID;

/**
 * Created by patrick on 7/8/14.
 */
public class Resource extends BaseRubyModel {

    private MapTile maptile;
    private ID id;
    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
     this.id = id;
    }

    @Override
    public void setRubyContext(RubyContext context) {

    }

    @Override
    public boolean Save() {
        return false;
    }
}
