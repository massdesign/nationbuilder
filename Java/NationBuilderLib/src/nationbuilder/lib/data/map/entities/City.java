package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Entity;

/**
 * Created by patrick on 1/5/15.
 */
@Entity(tableName = "cities")
public class City extends GameEntity
{
	// The name of the city
//	private String name;
	// The building that are part of the city and its administration (does not mean that these have to be in the same place)
//	private List<Building> buildings;
	// The tiles that fall under the administration of the city (initially the land on which the city is build) but can also be expanded later on
	private List<Tile> locations;
	// The contracts where the city is involved
	private List<Contract> contracts;

	// population of the city.. not sure if this is the way we are going to handle this
	private int population;

	public City()
	{
		this.contracts = new ArrayList<>();
	}

	public int getPopulation()
	{
		return population;
	}

	public void setPopulation(int population)
	{
		this.population = population;
	}
	/*
	public List<Building> getBuildings()
	{
		return buildings;
	}

	public void addBuilding(Building building)
	{
		this.buildings.add(building);
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
	*/
}
