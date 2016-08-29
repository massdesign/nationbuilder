package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_relay_station_types")
public class PowerRelayStationType extends BaseRubyModel implements NamedObject
{
	@Column
	private int capacity;
	@Column
	private String name;
	@Column
	private String responsetime;
	@Column
	private String powerplantType;


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
		return responsetime;
	}

	public void setResponseTime(String responseTime)
	{
		this.responsetime = responseTime;
	}

	public String getPowerplantType()
	{
		return powerplantType;
	}

	public void setPowerplantType(String powerplantType)
	{
		this.powerplantType = powerplantType;
	}
}
