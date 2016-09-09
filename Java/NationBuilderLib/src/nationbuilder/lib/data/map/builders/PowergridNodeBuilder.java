package nationbuilder.lib.data.map.builders;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Building;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;

/**
 * @author patrick.ekkel
 */
public class PowergridNodeBuilder
{

	private RubyContext rubyContext;
	public PowergridNodeBuilder(RubyContext rubyContext) {
		this.rubyContext = rubyContext;
	}

	public PowerGridNode createPowerGridNode(Building building) {

		PowerGridNode result = this.rubyContext.createRubyModel(PowerGridNode.class);

		if(building instanceof EnergyBuilding) {
		    EnergyBuilding energyBuilding = (EnergyBuilding)building;
			energyBuilding.setPowerGridNode(result);
		}
		else if(building instanceof PowerRelayStation) {

			PowerRelayStation powerRelayStation = (PowerRelayStation)building;
			powerRelayStation.setPowerGridNode(result);
		}
		return result;
	}

}
