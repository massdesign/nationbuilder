package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */
/*
	A Node is used to bring several connections together. A power station can be coupled to more grids with a node
 */
public class Node extends BaseRubyModel
{
	/**
	 * In this case this will most likely be a generated identifier
	 */
	private String name;

	@OneToOne
	private NodeType nodeType;

	private int load;
	public NodeType getNodeType()
	{
		return nodeType;
	}

	public void setNodeType(NodeType nodeType)
	{
		this.nodeType = nodeType;
	}


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getLoad()
	{
		return load;
	}

	public void setLoad(int load)
	{
		this.load = load;
	}
}
