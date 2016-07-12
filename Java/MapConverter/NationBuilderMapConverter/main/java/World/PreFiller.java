package World;

import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import java.util.ArrayList;

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
        this.rubyModels = new ArrayList<>();

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

        /*try {
            image.Save("/images/");
            tile.Save("/tiles/");

            this.context.commit();
            //state.Save("/states/");
            //claim.setClaimedTile(tile);
            //claim.setClaimedBy(state);

           // claim.Save("/claims/");
        } catch (RubyException e) {
            e.printStackTrace();
        }*/
	}
    public void testFill(MapDataset dataset)
    {



    }
    private void save()
    {

    }


    private TerrainType createTerrainType(String name)
    {
        TerrainType result = context.createRubyModel(TerrainType.class);
        result.setName(name);

        return result;
    }
    private void fillTerrainTypes()
    {
        // TODO: misschien zijn we dit ook niet meer nodig met de nieuwe filler
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
