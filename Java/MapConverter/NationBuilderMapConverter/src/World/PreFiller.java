package World;


import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 7/14/14.
 */
public class PreFiller {

    private RubyContext context;
    private ArrayList<BaseRubyModel> rubyModels;

	private UserFiller userFiller;
	private RegimeFiller regimeFiller;
	private ReligionFiller religionFiller;
	private EnergyBuildingFiller energyBuildingFiller;

    public PreFiller(RubyContext context)
    {
        this.context = context;
        this.rubyModels = new ArrayList<BaseRubyModel>();

		this.userFiller = new UserFiller(context);
		this.regimeFiller = new RegimeFiller(context);
		this.religionFiller = new ReligionFiller(context);
		this.energyBuildingFiller = new EnergyBuildingFiller(context);

    }

    public void Fill()
    {
		userFiller.Fill();
        energyBuildingFiller.Fill();
       	// TODO: refactor these models also in the new BaseFiller model
        fillTerrainTypes();
        fillResourceTypes();
        this.save();
    }

	public void testFill()
	{
        Tile tile = this.context.createRubyModel(Tile.class);
        Claim claim = this.context.createRubyModel(Claim.class);
        State state = this.context.createRubyModel(State.class);

        state.setName("henk");

        try {
            tile.Save("/tiles/");
            state.Save("/states/");
            claim.setClaimedTile(tile);
            claim.setClaimedBy(state);

            claim.Save("/claims/");
        } catch (RubyException e) {
            e.printStackTrace();
        }
	}
    public void testFill(MapDataset dataset)
    {



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
/*
    private Tile createMapTile()
    {
        Tile result = this.context.createRubyModel(Tile.class);
        result.setXoffset(1);
        result.setYoffset(1);
        result.setGidtag(2);
        result.setYposition(3);
        result.setXposition(4);
        return result;
    }*/

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

		this.userFiller.Save(Currency.class,"/currencies/");
		this.userFiller.Save(State.class,"/states/");
		this.userFiller.Save(User.class,"/users/");


        this.energyBuildingFiller.Save(EnergyBuildingType.class,"/energy_building_types");
        this.energyBuildingFiller.Save(EnergyBuilding.class,"/energy_building_types");


        String resourceTypeUrl = "/resourcetypes";
        String terrainTypeUrl = "/terraintypes";
        String energyBuildingTypeUrl = "/energy_building_types";
        String energyBuildingUrl = "/energy_building_types";
        for(BaseRubyModel type : rubyModels)
        {
			try
			{
				// TODO: dit kan handiger.. als resourceURL weggerefactored is kan dit ook simpeler
				if (type instanceof TerrainType)
				{
					type.Save(terrainTypeUrl);
				}
				else if (type instanceof ResourceType)
				{
					type.Save(resourceTypeUrl);
				}
				else if (type instanceof EnergyBuildingType)
				{
					type.Save(energyBuildingTypeUrl);
				}
				else if (type instanceof EnergyBuilding)
				{
					type.Save(energyBuildingUrl);
				}
			}
			catch (RubyException ex)
			{
				Log.write(ex,LogType.ERROR);
			}

        }


    }

}
