package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;

/**
 * @author patrick.ekkel
 */
// TODO: energy_building is op een rare manier opgeslagen niet zoals de andere buildings.. dit moeten we rechtzetten en energybuilding in lijn brengen met de rest van de code
@Entity(tableName = "energy_buildings")
public class EnergyBuilding extends Building
{
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
