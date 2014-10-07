package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.IgnoreInRails;
import nationbuilder.lib.Ruby.Association.annotation.ManyToMany;
import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 10/6/14.
 */
public class Religion extends BaseRubyModel
{
	private String bid;

	@IgnoreInRails
	@ManyToMany(mapIdTo = "bid")
	private ReligionBuildingType religionBuildingType;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public ReligionBuildingType getReligionBuildingType()
	{
		return religionBuildingType;
	}

	public void setReligionBuildingType(ReligionBuildingType religionBuildingType)
	{
		this.religionBuildingType = religionBuildingType;
	}


	private String name;


}
