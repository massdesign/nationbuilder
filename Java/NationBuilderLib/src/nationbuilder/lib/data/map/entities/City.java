package nationbuilder.lib.data.map.entities;

import java.util.List;
import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 1/5/15.
 */
public class City extends BaseRubyModel
{
	// The name of the city
	private String name;
	// The building that are part of the city and its administration (does not mean that these have to be in the same place)
	private List<Building> buildings;
	// infrastructure connected to the city (trade routes,railways,highways etc)
	private List<Connection> connections;
	// The tiles that fall under the administration of the city (initially the land on which the city is build) but can also be expanded later on
	private List<Tile> locations;
	//
	private List<Contract> contracts;


	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public List<Building> getBuildings()
	{
		return buildings;
	}

	public void setBuildings(List<Building> buildings)
	{
		this.buildings = buildings;
	}

	public List<Connection> getConnections()
	{
		return connections;
	}

	public void setConnections(List<Connection> connections)
	{
		this.connections = connections;
	}

	public List<Tile> getLocations()
	{
		return locations;
	}

	public void setLocations(List<Tile> locations)
	{
		this.locations = locations;
	}

	public List<Contract> getContracts()
	{
		return contracts;
	}

	public void setContracts(List<Contract> contracts)
	{
		this.contracts = contracts;
	}
}
