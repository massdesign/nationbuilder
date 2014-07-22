package World;

import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.ResourceType;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;
import java.util.ArrayList;

/**
 * Created by patrick on 7/14/14.
 */
public class Filler {

    private RubyContext context;
    private ArrayList<BaseRubyModel> rubyModels;
    public Filler(RubyContext context)
    {
        this.context = context;
        this.rubyModels = new ArrayList<BaseRubyModel>();
    }

    public void Fill()
    {

        fillTerrainTypes();
        fillResourceTypes();
        fillResourceTypes();

        this.save();
    }
    private void fillResourceTypes()
    {
        this.rubyModels.add(createResourceType("Oil",false,RESOURCELOCATION.SUBTERRAINIAN));
        this.rubyModels.add((createResourceType("Gold", false, RESOURCELOCATION.SUBSURFACE)));
        this.rubyModels.add(createResourceType("Iron",false,RESOURCELOCATION.EMBEDDEDROCK));
        this.rubyModels.add(createResourceType("Natural Gas", false, RESOURCELOCATION.CRUST));
    }
    private void fillTerrainTypes()
    {

        this.rubyModels.add(createTerrainType("WATER"));
        this.rubyModels.add(createTerrainType("SEA"));
        this.rubyModels.add(createTerrainType("FOREST"));
        this.rubyModels.add(createTerrainType("URBAN"));
        this.rubyModels.add(createTerrainType("MOUNTAIN"));
        this.rubyModels.add(createTerrainType("PLAINLAND"));
        this.rubyModels.add(createTerrainType("COAST"));
        this.rubyModels.add(createTerrainType("NONE"));
    }

    private TerrainType createTerrainType(String name)
    {
        TerrainType result = context.createRubyModel(TerrainType.class);
        result.setName(name);

        return result;
    }
    private  ResourceType createResourceType(String name,boolean regenerateing,RESOURCELOCATION location)
    {
        ResourceType result =  context.createRubyModel(ResourceType.class);
        result.setName(name);
        result.setRegenerating(regenerateing);
        result.setLocation(location);
        return result;
    }
    private void save()
    {
        String resourceTypeUrl = "/resourcetypes";
        String terrainTypeUrl = "/terraintypes";
        for(BaseRubyModel type : rubyModels)
        {
            // TODO: dit kan handiger.. als resourceURL weggerefactored is kan dit ook simpeler
            if(type instanceof  TerrainType)
            {
                type.Save(terrainTypeUrl);
            }
            else if(type instanceof  ResourceType)
            {
                type.Save(resourceTypeUrl);
            }

        }


    }

}
