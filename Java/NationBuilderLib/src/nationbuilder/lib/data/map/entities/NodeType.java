package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */
public class NodeType extends BaseRubyModel
{
	private String name;

	private boolean destroyable;

	public boolean isDestroyable()
	{
		return destroyable;
	}

	public void setDestroyable(boolean destroyable)
	{
		this.destroyable = destroyable;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
