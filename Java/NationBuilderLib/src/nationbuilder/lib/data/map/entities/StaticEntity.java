package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 9/19/14.
 */
public class StaticEntity extends BaseRubyModel
{
    @OneToOne(mapIdTo = "loid")
	private Tile locatedOn;

    private String loid;

	public Tile getLocatedOn()
	{
		return locatedOn;
	}

	public void setLocatedOn(Tile locatedOn)
	{
		this.locatedOn = locatedOn;
	}

}
