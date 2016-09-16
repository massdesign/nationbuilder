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

	public PowerGridNode createPowerGridNode(Building building,Tile tile) throws MapConvertException
	{

		PowerGridNode result = this.rubyContext.createRubyModel(PowerGridNode.class);

		List<TiledXmlProperty> properties = propertyManager.getObjectGroupProperties(tile.getXposition(), tile.getYposition());
		HashMap<TilePropertyType, String> mappedProperties = mapProperties(properties);


		String propertyValue =   mappedProperties.get(TilePropertyType.SUBSTATION_CONNECTIONS)
								 != null ? mappedProperties.get(TilePropertyType.SUBSTATION_CONNECTIONS) :  mappedProperties.get(TilePropertyType.POWERPLANT_CONNECTIONS);

		if(propertyValue != null) {


			String [] connections = propertyValue.split(",");

			for (String connection : connections)
			{

				EnergyBuilding energyBuilding =  getExistingRubyObject(connection,EnergyBuilding.class);
				PowerGridComponent powerGridComponent = energyBuilding;
				if(powerGridComponent == null) {
					PowerRelayStation powerRelayStation = getExistingRubyObject(connection, PowerRelayStation.class);
					powerGridComponent  = powerRelayStation;
					if(powerGridComponent != null)
					{
						PowerConnection powerConnection = this.rubyContext.createRubyModel(PowerConnection.class);
						PowerGridNode powerGridNodeFrom = powerGridComponent.getPowergridNode();
						PowerGridNode powerGridNodeTo = result;

						powerConnection.setA(powerGridNodeFrom);
						powerConnection.setB(powerGridNodeTo);
						powerGridComponent.addConnection(powerConnection);


					}

					// TODO: city connections toevoegen.. Omdat substations meestal naar cities verbinden. Die moeten dus ook een powergridnode gaan bevatten
				}


			}


		}

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
