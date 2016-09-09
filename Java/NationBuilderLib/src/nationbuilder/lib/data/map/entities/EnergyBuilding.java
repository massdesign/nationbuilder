package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
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
    // TODO: zie todo hierboven, tableperclass structuur hier aanbrengen
    public EnergyBuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(EnergyBuildingType buildingType) {
        this.buildingType = buildingType;
    }

    @OneToOne(mapIdTo = "pgid")
    private PowerGridNode powerGridNode;

    @Column
    @OneToOne(mapIdTo = "btid")
	private EnergyBuildingType buildingType;


    public PowerGridNode getPowerGridNode()
    {
        return powerGridNode;
    }

    public void setPowerGridNode(PowerGridNode powerGridNode)
    {
        this.powerGridNode = powerGridNode;
    }
}
