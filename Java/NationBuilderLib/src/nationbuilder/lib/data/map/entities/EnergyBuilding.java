package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "energy_buildings",strategy = InhiritanceStrategy.TablePerClass)
public class EnergyBuilding extends Building implements PowerGridComponent
{
    // TODO: zie todo hierboven, tableperclass structuur hier aanbrengen
    public EnergyBuildingType getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(EnergyBuildingType buildingType) {
        this.buildingType = buildingType;
    }

    @Column
    @OneToOne(mapIdTo = "btid")
	private EnergyBuildingType buildingType;
    private String btid;
}
