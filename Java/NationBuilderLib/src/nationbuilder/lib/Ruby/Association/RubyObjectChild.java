package nationbuilder.lib.Ruby.Association;

import java.util.List;

/**
 * Created by patrick on 9/15/14.
 */
public class RubyObjectChild
{
	public List<RubyObjectChild> getChildren()
	{
		return children;
	}

	public void setChildren(List<RubyObjectChild> children)
	{
		this.children = children;
	}

	private List<RubyObjectChild> children;
}
