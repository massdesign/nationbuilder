package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.BaseRubyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 7/15/14.
 */
public class Resource extends BaseRubyModel {


    private List<ResourceType> resourceTypes = new ArrayList<ResourceType>();
   // private ResourceType resourceType;
    private TerrainType terrainType;

    // terraintype for reference in backend
    private String tti;
    // resourcetype for reference in backend
    private int [] rtis;
    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }


    public void addResourceType(ResourceType resource)
    {
        this.resourceTypes.add(resource);
    }
  //  public ResourceType getResourceType() {
      //  return resourceType;
   // }

     //public void setResourceType(ResourceType resourceType) {
    //    this.resourceType = resourceType;
  //  }

    public void fetchIDs()
    {
       this.rtis = RubyAssociationResolver.CreateIDsFromArrayList(resourceTypes);
       //this.rti = this.getResourceType().getId().getId();
       this.tti = this.getTerrainType().getId().getId();
    }
}
