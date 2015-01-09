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

    @OneToMany(mapIdTo = "rtis")
    private List<ResourceType> resourceTypes = new ArrayList<ResourceType>();
	@OneToOne(mapIdTo = "tti")
    private TerrainType terrainType;

    // terraintype for reference in backend
    private String tti;
    // resourcetype for reference in backend
    private int [] rtis;
    public TerrainType getTerrainType() {
        return terrainType;
    }

    public void setTerrainType(TerrainType terrainType) {
        if(terrainType == null)
        {
            System.out.println("biertje");
        }
        Log.writeInfo("Teraintype: " + terrainType.getName());
        this.terrainType = terrainType;
    }

    public void addResourceType(ResourceType resource)
    {
        this.resourceTypes.add(resource);
    }
}
