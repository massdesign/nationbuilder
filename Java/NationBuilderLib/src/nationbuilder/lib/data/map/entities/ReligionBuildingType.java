package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * Created by patrick on 10/7/14.
 */
public class ReligionBuildingType extends BaseRubyModel
{

	private String name;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
