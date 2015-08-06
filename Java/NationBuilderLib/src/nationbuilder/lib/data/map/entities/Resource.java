package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.BaseRubyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 7/15/14.
 */
@Entity(tableName = "resources")
public class Resource extends BaseRubyModel {

   // @OneToMany(mapIdTo = "rtis")
   // private List<ResourceType> resourceTypes = new ArrayList<ResourceType>();
    @OneToOne(mapIdTo = "rti")
    private ResourceType resourceType;

   // private int [] rtis;
    // resourcetype for reference in backend
    private String rti;
  /*  public TerrainType getTerrainType() {
        return terrainType;
    }*/



    public ResourceType getResourceType()
    {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType)
    {
        this.resourceType = resourceType;
    }
}
