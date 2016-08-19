package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_relay_station_types")
public class PowerRelayStationType extends BaseRubyModel
{
	private int capacity;
	private String name;
	private String responseTime;


	public int getCapacity()
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getResponseTime()
	{
		return responseTime;
	}

	public void setResponseTime(String responseTime)
	{
		this.responseTime = responseTime;
	}
}
