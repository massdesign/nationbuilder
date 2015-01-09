package nationbuilder.lib.data.map.entities;

/**
 * Created by patrick on 9/18/14.
 */

import nationbuilder.lib.Ruby.Association.annotation.IgnoreInRails;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Used as a base class for all actors in the game..
 */
public class GameEntity extends BaseRubyModel
{

	private String name;
	@IgnoreInRails
	private String rby;
	@IgnoreInRails
	@OneToOne(mapIdTo = "rby")
	private User ruledBy;
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public User getRuledBy()
	{
		return ruledBy;
	}

	public void setRuledBy(User ruledBy)
	{
		this.ruledBy = ruledBy;
	}

}
