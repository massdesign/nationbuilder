package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * Created by patrick on 9/23/14.
 */
// TODO: energybuilding wordt niet meer gebruikt in zijn huidige vorm... we zouden dit dus kunnen deleten
@Entity(tableName = "energy_building_types")
public class EnergyBuildingType extends BaseRubyModel
{
	private String name;
    /**
     * Power output is measures per game turn in megawatt
     */
	@Column
	private int powerOutput;
	@Column
	private String energySource;

	public String getEnergySource()
	{
		return energySource;
	}

	public void setEnergySource(String energySource)
	{
		this.energySource = energySource;
	}

	public int getPowerOutput()
	{
		return powerOutput;
	}

	public void setPowerOutput(int powerOutput)
	{
		this.powerOutput = powerOutput;
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
