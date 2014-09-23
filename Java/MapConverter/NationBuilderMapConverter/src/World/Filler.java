package World;

import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by patrick on 7/14/14.
 */
public class Filler {

    private RubyContext context;
    private ArrayList<BaseRubyModel> rubyModels;
    private List<EnergyBuildingType> energyBuildingTypes;
    public Filler(RubyContext context)
    {
        this.context = context;
        this.rubyModels = new ArrayList<BaseRubyModel>();
        this.energyBuildingTypes = new ArrayList<>();
    }

    public void Fill()
    {
        fillTerrainTypes();
        fillResourceTypes();
        fillEnergyBuildingTypes();
        fillEnergyBuildings();

        this.save();
    }

	public void testFill()
	{
        MapTile mt1 = createMapTile();
        EnergyBuildingType ebt1 =   createEnergyBuildingType("test plant",100,"Beer");
        EnergyBuilding eb1 = createEnergyBuilding("Ijssel centrale");
        eb1.setBuildingType(ebt1);
        eb1.setLocatedOn(mt1);
        mt1.Save("/tiles/");
        ebt1.Save("/energy_building_types");
        eb1.Save("/energy_buildings");
    }
    private void fillResourceTypes()
    {
        this.rubyModels.add(createResourceType("Oil", false, RESOURCELOCATION.SUBTERRAINIAN));
        this.rubyModels.add((createResourceType("Gold", false, RESOURCELOCATION.SUBSURFACE)));
        this.rubyModels.add(createResourceType("Iron", false, RESOURCELOCATION.EMBEDDEDROCK));
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
	private void fillEnergyBuildingTypes()
	{
		this.rubyModels.add(createEnergyBuildingType("Nuclear power plant MK 1", 400, "Nuclear"));
		this.rubyModels.add(createEnergyBuildingType("Nuclear power plant MK 2", 1000, "Nuclear"));
		this.rubyModels.add(createEnergyBuildingType("Nuclear power plant MK 3", 3550, "Nuclear"));
		this.rubyModels.add(createEnergyBuildingType("Nuclear power plant MK 4", 6520, "Nuclear"));
		this.rubyModels.add(createEnergyBuildingType("Nuclear power plant MK 5", 7100, "Nuclear"));
		this.rubyModels.add(createEnergyBuildingType("Gas Turbine Generator MK 1", 30, "Natural gas"));
		this.rubyModels.add(createEnergyBuildingType("Gas Turbine Generator MK 2", 100, "Natural gas"));
		this.rubyModels.add(createEnergyBuildingType("Gas Turbine Generator MK 3", 250, "Natural gas"));
		this.rubyModels.add(createEnergyBuildingType("Gas Turbine Generator MK 4", 450, "Natural gas"));
		this.rubyModels.add(createEnergyBuildingType("Gas Turbine Generator MK 5", 682, "Natural gas"));
		this.rubyModels.add(createEnergyBuildingType("Gas Turbine Generator MK 6", 782, "Natural gas"));

        this.rubyModels.add(createEnergyBuildingType("Coal Power Station MK 1", 800, "Coal"));
        this.rubyModels.add(createEnergyBuildingType("Coal Power Station MK 2", 1600, "Coal"));
        this.rubyModels.add(createEnergyBuildingType("Coal Power Station MK 3", 2000, "Coal"));
        this.rubyModels.add(createEnergyBuildingType("Coal Power Station MK 4", 4000, "Coal"));
        this.rubyModels.add(createEnergyBuildingType("Coal Power Station MK 5", 5600, "Coal"));

        this.rubyModels.add(createEnergyBuildingType("Biomass Power Station MK 1", 100, "Biomass"));
        this.rubyModels.add(createEnergyBuildingType("Biomass Power Station MK 2", 225, "Biomass"));
        this.rubyModels.add(createEnergyBuildingType("Biomass Power Station MK 3", 450, "Biomass"));
        this.rubyModels.add(createEnergyBuildingType("Biomass Power Station MK 4", 600, "Biomass"));
        this.rubyModels.add(createEnergyBuildingType("Biomass Power Station MK 5", 760, "Biomass"));
      //  this.rubyModels.add(createEnergyBuildingType("Geothermal Power Station MK 1",))
	}
    private void fillEnergyBuildings()
    {
        this.rubyModels.add(createEnergyBuilding("IjsselCentrale"));
        this.rubyModels.add(createEnergyBuilding("MoerdijkCentrale"));
    }
    private MapTile createMapTile()
    {
        MapTile result = this.context.createRubyModel(MapTile.class);
        result.setXoffset(1);
        result.setYoffset(1);
        result.setGidtag(2);
        result.setYposition(3);
        result.setXposition(4);
        return result;
    }
    private EnergyBuilding createEnergyBuilding(String name)
    {
        Random rand = new Random();

        int next = rand.nextInt(this.energyBuildingTypes.size()-1);

        EnergyBuilding result = this.context.createRubyModel(EnergyBuilding.class);
        result.setName(name);
        result.setBuildingType(this.energyBuildingTypes.get(next));

        return result;
    }
	private EnergyBuildingType createEnergyBuildingType(String name, int outputinMW, String energysource)
	{
		EnergyBuildingType result = this.context.createRubyModel(EnergyBuildingType.class);
		result.setPowerOutput(outputinMW);
		result.setName(name);
		result.setEnergySource(energysource);
        this.energyBuildingTypes.add(result);
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
        String energyBuildingTypeUrl = "/energy_building_types";
        String energyBuildingUrl = "/energy_buildings";
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
            else if(type instanceof  EnergyBuildingType)
            {
                type.Save(energyBuildingTypeUrl);
            }
            else if(type instanceof EnergyBuilding)
            {
                type.Save(energyBuildingUrl);
            }

        }


    }

}
