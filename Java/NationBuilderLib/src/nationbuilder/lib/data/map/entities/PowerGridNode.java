package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Association.annotation.Transient;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_grid_nodes",strategy = InhiritanceStrategy.TablePerClass)
public class PowerGridNode extends Node
{
    //@Transient
	//private String rsid;
	//@Transient
	//private String ebid;
	// TODO: one to one hier uitbreiden
	//@OneToOne(mapIdTo = "rsid")
	//private PowerRelayStation relayStation;

	//@OneToOne(mapIdTo = "ebid")
	//private EnergyBuilding energyBuilding;

	/*public PowerRelayStation getRelayStation()
	{
		return relayStation;
	}

	public void setRelayStation(PowerRelayStation relayStation)
	{
		this.relayStation = relayStation;
	}

	public EnergyBuilding getEnergyBuilding()
	{
		return energyBuilding;
	}

	public void setEnergyBuilding(EnergyBuilding energyBuilding)
	{
		this.energyBuilding = energyBuilding;
	}*/
}
