package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 9/19/14.
 */
public class StaticEntity extends BaseRubyModel
{
	private MapTile locatedOn;

	public MapTile getLocatedOn()
	{
		return locatedOn;
	}

	public void setLocatedOn(MapTile locatedOn)
	{
		this.locatedOn = locatedOn;
	}

}
