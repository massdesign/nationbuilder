package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 9/19/14.
 */
public class StaticEntity extends BaseRubyModel
{
    @OneToOne(mapIdTo = "loid")
	private MapTile locatedOn;

    private String loid;

	public MapTile getLocatedOn()
	{
		return locatedOn;
	}

	public void setLocatedOn(MapTile locatedOn)
	{
		this.locatedOn = locatedOn;
	}

}
