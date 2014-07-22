package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 7/15/14.
 */
public class Resource extends BaseRubyModel {

    private ResourceType resourceType;
    private TerrainType terrainType;

    // terraintype for reference in backend
    private String tti;
    // resourcetype for reference in backend
    private String rti;
    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        this.terrainType = terrainType;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public void fetchIDs()
    {
       this.rti = this.getResourceType().getId().getId();
       this.tti = this.getTerrainType().getId().getId();
    }
}
