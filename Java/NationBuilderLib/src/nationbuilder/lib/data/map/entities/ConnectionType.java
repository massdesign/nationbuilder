package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */

/**
 * Describes what type of connection you are looking at,
 */
public class ConnectionType extends BaseRubyModel
{
	/**
	 *
	 */
	private String name;

	/**
	 * Can this connectiontype be touched by Physical violence
	 */
	private boolean destroyable;


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public boolean isDestroyable()
	{
		return destroyable;
	}

	public void setDestroyable(boolean destroyable)
	{
		this.destroyable = destroyable;
	}
}
