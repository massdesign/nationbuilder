package nationbuilder.lib.data.map.builders;

import java.util.HashMap;
import java.util.List;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Building;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
import nationbuilder.lib.data.map.entities.PowerConnection;
import nationbuilder.lib.data.map.entities.PowerGridComponent;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.exceptions.MapConvertException;
import nationbuilder.lib.data.map.mapservice.TilePropertyType;
import nationbuilder.lib.data.map.mapservice.TiledXmlProperty;

/**
 * @author patrick.ekkel
 */
public class PowergridNodeBuilder extends BaseBuilder
{

	public PowergridNodeBuilder(RubyContext rubyContext) throws RubyDataServiceNotInitializedException
	{
		super(rubyContext);

	}

	public PowerGridNode createPowerGridNode(Building building) throws MapConvertException
	{

		PowerGridNode result = this.rubyContext.createRubyModel(PowerGridNode.class);

		result.setName(building.getName());
		if(building instanceof EnergyBuilding) {
		    EnergyBuilding energyBuilding = (EnergyBuilding)building;
			energyBuilding.setPowerGridNode(result);
		}
		else if(building instanceof PowerRelayStation) {

			PowerRelayStation powerRelayStation = (PowerRelayStation)building;
			powerRelayStation.setPowerGridNode(result);
		}

		// add connections


		return result;
	}

}
