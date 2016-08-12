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
		userFiller.Fill();
        energyBuildingFiller.Fill();
        cityFiller.Fill();
        warehouseFiller.Fill();
    }

	public void testFill()
	{

	}
    public void testFill(MapDataset dataset)
    {



    }

}
