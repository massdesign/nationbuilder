package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;

/**
 * Created by patrick on 9/19/14.
 */
@Entity(tableName = "energy_buildings")
public class EnergyBuilding extends Building
{
    public EnergyBuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(EnergyBuildingType buildingType) {
        this.buildingType = buildingType;
    }

    @OneToOne(mapIdTo = "btid")
	private EnergyBuildingType buildingType;
    private Connection connection;
    private String btid;
	public Connection getConnection()
	{
		return connection;
	}
	public void setConnection(Connection connection)
	{
		this.connection = connection;
	}
}
