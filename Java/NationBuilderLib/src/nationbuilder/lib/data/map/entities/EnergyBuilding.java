package nationbuilder.lib.data.map.entities;

/**
 * Created by patrick on 9/19/14.
 */
public class EnergyBuilding extends Building
{
	/**
	 * Power output is measures per game turn in megawatt
	 */
	private int poweroutput;
	private String type;
	public int getPowerload()
	{
		return powerload;
	}

	public void setPowerload(int powerload)
	{
		this.powerload = powerload;
	}

	public int getPoweroutput()
	{
		return poweroutput;
	}

	public void setPoweroutput(int poweroutput)
	{
		this.poweroutput = poweroutput;
	}

	public Connection getConnection()
	{
		return connection;
	}

	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	private int powerload;
	private Connection connection;
}
