package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;

/**
 * Created by patrick on 9/18/14.
 */

public class Building extends StaticEntity
{

	private String geo;

	private String name;
	//private Contract contract;
	@ManyToOne(mapIdTo = "geo")
	private GameEntity owner;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public GameEntity getOwner()
	{
		return owner;
	}

	public void setOwner(GameEntity owner)
	{
		this.owner = owner;
	}

//	public Contract getContract()
	//{
	//	return contract;
	//}

	//public void setContract(Contract contract)
	//{
	//	this.contract = contract;
	//}
}
