package World;


import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 7/14/14.
 */
public class    PreFiller {

    private RubyContext context;
    private ArrayList<BaseRubyModel> rubyModels;

	private UserFiller userFiller;
	private RegimeFiller regimeFiller;
	private ReligionFiller religionFiller;
	private EnergyBuildingFiller energyBuildingFiller;

    private CityFiller cityFiller;
    private WarehouseFiller warehouseFiller;
    public PreFiller(RubyContext context)
    {
        this.context = context;
        this.rubyModels = new ArrayList<BaseRubyModel>();

		this.userFiller = new UserFiller(context);
		this.regimeFiller = new RegimeFiller(context);
		this.religionFiller = new ReligionFiller(context);
		this.energyBuildingFiller = new EnergyBuildingFiller(context);
        this.warehouseFiller = new WarehouseFiller(context);
        this.cityFiller = new CityFiller(context);
    }

    public void Fill() throws ObjectConversionFailedException {
        fillTerrainTypes();

		userFiller.Fill();
        energyBuildingFiller.Fill();
        cityFiller.Fill();
        warehouseFiller.Fill();

        this.save();
    }

	public void testFill()
	{
        Tile tile = this.context.createRubyModel(Tile.class);
        Image image = this.context.createRubyModel(Image.class);

        image.setName("Crazy images");
        image.setTileHeight(32);
        image.setTileWidth(32);
      //  tile.setImage(image);
       // Claim claim = this.context.createRubyModel(Claim.class);
        //State state = this.context.createRubyModel(State.class);

        //state.setName("henk");
        tile.setImage(image);
        tile.setXoffset(232);
        tile.setYoffset(900);
        tile.setXposition(392);
        tile.setYposition(32);

        try {
            image.Save("/images/");
            tile.Save("/tiles/");

            this.context.commit();
            //state.Save("/states/");
            //claim.setClaimedTile(tile);
            //claim.setClaimedBy(state);

           // claim.Save("/claims/");
        } catch (RubyException e) {
            e.printStackTrace();
        }
	}
    public void testFill(MapDataset dataset)
    {



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


    private void save()
    {

		this.userFiller.Save(Currency.class,"/currencies/");
		this.userFiller.Save(State.class,"/states/");
		this.userFiller.Save(User.class,"/users/");


        this.energyBuildingFiller.Save(EnergyBuildingType.class,"/energy_building_types");
        this.energyBuildingFiller.Save(EnergyBuilding.class,"/energy_building_types");

        this.cityFiller.Save(City.class,"/cities/");
        this.warehouseFiller.Save(WareHouse.class,"/warehouses/");




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


    private TerrainType createTerrainType(String name)
    {
        TerrainType result = context.createRubyModel(TerrainType.class);
        result.setName(name);

        return result;
    }
    private void fillTerrainTypes()
    {

        this.rubyModels.add(createTerrainType("WATER"));
        this.rubyModels.add(createTerrainType("SEA"));
        this.rubyModels.add(createTerrainType("INLANDCOAST"));
        this.rubyModels.add(createTerrainType("INLANDSEA"));
        this.rubyModels.add(createTerrainType("RIVER"));
        this.rubyModels.add(createTerrainType("FOREST"));
        this.rubyModels.add(createTerrainType("URBAN"));
        this.rubyModels.add(createTerrainType("MOUNTAIN"));
        this.rubyModels.add(createTerrainType("PLAINLAND"));
        this.rubyModels.add(createTerrainType("COAST"));
        this.rubyModels.add(createTerrainType("NONE"));
    }

}
