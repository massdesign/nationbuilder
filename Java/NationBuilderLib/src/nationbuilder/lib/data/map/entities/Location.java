package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 3/19/15.
 */
public class Location extends BaseRubyModel {

    private List<Tile> tiles;

    public Location()
    {
        this.tiles = new ArrayList<>();
    }

}
