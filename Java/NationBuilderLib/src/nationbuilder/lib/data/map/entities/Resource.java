package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.MappedBy;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * Created by patrick on 7/15/14.
 */
@Entity(tableName = "resources")
public class Resource extends BaseRubyModel {


   // @OneToMany(mapIdTo = "rtis")
   // private List<ResourceType> resourceTypes = new ArrayList<ResourceType>();
    @OneToOne(mapIdTo = "rti")
    private ResourceType resourceType;


    @ManyToOne(mapIdTo = MappedBy.SELF)
    private ReferenceMapping tile;
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
