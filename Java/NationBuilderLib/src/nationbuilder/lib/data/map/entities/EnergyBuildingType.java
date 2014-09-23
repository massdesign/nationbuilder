package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 9/23/14.
 */
public class EnergyBuildingType extends BaseRubyModel
{
	private String name;
    /**
     * Power output is measures per game turn in megawatt
     */
	private int powerOutput;
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
