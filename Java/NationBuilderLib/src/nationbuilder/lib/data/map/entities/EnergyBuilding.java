package nationbuilder.lib.data.map.entities;

/**
 * Created by patrick on 9/19/14.
 */
public class EnergyBuilding extends Building
{
	/**
	 * Power output is measures per game turn in megawatt
	 */
	private String type;
	private EnergyBuildingType buildingType;


	public Connection getConnection()
	{
		return connection;
	}

	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}

	private Connection connection;
}
