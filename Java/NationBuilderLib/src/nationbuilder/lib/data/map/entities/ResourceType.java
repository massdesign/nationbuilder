package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;

/**
 * Created by patrick on 7/8/14.
 */
public class ResourceType extends BaseRubyModel {

    private String name;
    private boolean regenerating;
    private RESOURCELOCATION location;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRegenerating() {
        return regenerating;
    }

    public void setRegenerating(boolean regenerating) {
        this.regenerating = regenerating;
    }
    public RESOURCELOCATION getLocation() {
        return location;
    }

    public void setLocation(RESOURCELOCATION location) {
        this.location = location;
    }
}