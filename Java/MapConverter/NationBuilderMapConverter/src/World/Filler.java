package World;

import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Building;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
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
        fillBuildings();

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
	private void fillBuildings()
	{
		this.rubyModels.add(createEnergyBuilding("Nuclear power plant MK 1",400,"Nuclear"));
		this.rubyModels.add(createEnergyBuilding("Nuclear power plant MK 2", 1000, "Nuclear"));
		this.rubyModels.add(createEnergyBuilding("Nuclear power plant MK 3", 3550, "Nuclear"));
		this.rubyModels.add(createEnergyBuilding("Nuclear power plant MK 4", 6520, "Nuclear"));
		this.rubyModels.add(createEnergyBuilding("Nuclear power plant MK 5", 7100, "Nuclear"));

        this.rubyModels.add(createEnergyBuilding("Gas Turbine Generator MK 1", 30, "Natural gas"));
		this.rubyModels.add(createEnergyBuilding("Gas Turbine Generator MK 2", 100, "Natural gas"));
		this.rubyModels.add(createEnergyBuilding("Gas Turbine Generator MK 3", 250, "Natural gas"));
		this.rubyModels.add(createEnergyBuilding("Gas Turbine Generator MK 4", 450, "Natural gas"));
		this.rubyModels.add(createEnergyBuilding("Gas Turbine Generator MK 5", 682, "Natural gas"));
		this.rubyModels.add(createEnergyBuilding("Gas Turbine Generator MK 6", 782, "Natural gas"));

        this.rubyModels.add(createEnergyBuilding("Coal Power Station MK 1",800,"Coal"));
        this.rubyModels.add(createEnergyBuilding("Coal Power Station MK 2",1600,"Coal"));
        this.rubyModels.add(createEnergyBuilding("Coal Power Station MK 3",2000,"Coal"));
        this.rubyModels.add(createEnergyBuilding("Coal Power Station MK 4",4000,"Coal"));
        this.rubyModels.add(createEnergyBuilding("Coal Power Station MK 5",5600,"Coal"));

        this.rubyModels.add(createEnergyBuilding("Biomass Power Station MK 1",100,"Biomass"));
        this.rubyModels.add(createEnergyBuilding("Biomass Power Station MK 2",225,"Biomass"));
        this.rubyModels.add(createEnergyBuilding("Biomass Power Station MK 3",450,"Biomass"));
        this.rubyModels.add(createEnergyBuilding("Biomass Power Station MK 4",600,"Biomass"));
        this.rubyModels.add(createEnergyBuilding("Biomass Power Station MK 5",760,"Biomass"));

        this.rubyModels.add(createEnergyBuilding("Geothermal Power Station MK 1",))


	}
	private EnergyBuilding createEnergyBuilding(String name,int outputinMW,String type)
	{
		EnergyBuilding result = new EnergyBuilding();
		result.setName(name);
		result.setPoweroutput(outputinMW);
		return result;
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
