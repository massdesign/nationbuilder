package World;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Religion;
import nationbuilder.lib.data.map.entities.ReligionBuilding;
import nationbuilder.lib.data.map.entities.ReligionBuildingType;

/**
 * Created by patrick on 10/6/14.
 */
public class ReligionFiller extends BaseFiller
{
	public ReligionFiller(RubyContext context)
	{
		super(context);
	}

	@Override
	public void Fill()
	{
		this.getRubyModels().add(createReligion("Moslim","Mosque"));
		this.getRubyModels().add(createReligion("Cristian","Church"));
		this.getRubyModels().add(createReligion("Buddhism","Temple"));
	}

	private Religion createReligion(String name,String religionbuilding)
	{
		Religion result = new Religion();
		ReligionBuildingType religionBuildingType = new ReligionBuildingType();
		religionBuildingType.setName(religionbuilding);
		result.setName(name);
		result.setReligionBuildingType(religionBuildingType);
		return result;
	}
}
