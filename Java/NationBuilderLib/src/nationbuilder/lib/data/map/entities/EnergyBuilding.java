package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Association.annotation.Transient;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "energy_buildings",strategy = InhiritanceStrategy.TablePerClass)
public class EnergyBuilding extends Building implements PowerGridComponent
{
    private String pgid;

    private String btid;

    private int[] pcids;
    public EnergyBuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(EnergyBuildingType buildingType) {
        this.buildingType = buildingType;
    }

    @OneToMany(mapIdTo = "pcids", mappedBy = "energy_building", mappedByClazz = PowerConnection.class)
    List<PowerConnection> powerConnections = new ArrayList<>();

    @OneToOne(mapIdTo = "pgid")
    private PowerGridNode powerGridNode;

    @Column
    @OneToOne(mapIdTo = "btid")
	private EnergyBuildingType buildingType;


    public PowerGridNode getPowergridNode()
    {
        return powerGridNode;
    }

    @Override
    public void addConnection(PowerConnection powerConnection)
    {
        powerConnections.add(powerConnection);
    }

    public void setPowerGridNode(PowerGridNode powerGridNode)
    {
        this.powerGridNode = powerGridNode;
    }
}
