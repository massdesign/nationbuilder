package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 7/1/14.
 */
@Entity(tableName = "terraintypes")
public class TerrainType extends BaseRubyModel {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

}
