package nationbuilder.lib.data.map.builders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
import nationbuilder.lib.data.map.entities.PowerConnection;
import nationbuilder.lib.data.map.entities.PowerGridComponent;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.exceptions.MapConvertException;
import nationbuilder.lib.data.map.mapservice.TilePropertyType;

/**
 * @author patrick.ekkel
 */
public class PowerConnectionBuilder extends BaseBuilder
{
	public PowerConnectionBuilder(RubyContext rubyContext) throws RubyDataServiceNotInitializedException
	{
		super(rubyContext);
	}


	public List<PowerConnection> createPowerConnections(PowerGridComponent from, String toConnections)
	{

		List<PowerConnection> result = new ArrayList<>();

		for (String connection : toConnections.split(","))
		{
			PowerConnection powerConnection = this.rubyContext.createRubyModel(PowerConnection.class);

			PowerGridNode toConnection = getExistingRubyObject(connection, PowerGridNode.class);
			powerConnection.setA(from.getPowergridNode());
			powerConnection.setB(toConnection);

			from.addConnection(powerConnection);

		}

		return result;

	}

	public List<PowerConnection> createConnections() throws MapConvertException
	{
		List<PowerConnection> powerConnections = new ArrayList<>();

		List<Integer> ids = propertyManager.getAllObjectIds();

		for (Integer integer : ids)
		{

			HashMap<TilePropertyType, String> mappedProperties = mapProperties(
			 propertyManager.getObjectGroupPropertiesById(integer));

			// determine if this is powerplant/substation object

			String powerplant_name = mappedProperties.get(TilePropertyType.POWERPLANT_NAME);
			String substation_name = mappedProperties.get(TilePropertyType.SUBSTATION_NAME);
			String powerplant_connections = mappedProperties.get(TilePropertyType.POWERPLANT_CONNECTIONS);
			String substation_connections = mappedProperties.get(TilePropertyType.SUBSTATION_CONNECTIONS);

			// it is a powerplant
			if (powerplant_name != null)
			{
				EnergyBuilding energyBuilding = getExistingRubyObject(powerplant_name, EnergyBuilding.class);
				powerConnections.addAll(
				 createPowerConnections(energyBuilding, mappedProperties.get(TilePropertyType.POWERPLANT_CONNECTIONS)));

			}
			// it is a substation
			else if (substation_name != null)
			{

				PowerRelayStation powerRelayStation = getExistingRubyObject(substation_name, PowerRelayStation.class);
				powerConnections.addAll(createPowerConnections(powerRelayStation,
				 mappedProperties.get(TilePropertyType.SUBSTATION_CONNECTIONS)));

			}
			// if none of the above complies.. its something else and we are not interested
		}
		return powerConnections;
	}

}
