package nationbuilder.lib.data.map.builders;

import java.util.List;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.entities.PowerRelayStationType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.exceptions.MapConvertException;
import nationbuilder.lib.data.map.mapservice.TileProperty;
import nationbuilder.lib.data.map.mapservice.TilePropertyType;
import nationbuilder.lib.data.map.mapservice.TiledPropertyManager;
import nationbuilder.lib.data.map.mapservice.TiledXmlProperty;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.XmlTile;

/**
 * @author patrick.ekkel
 */
public class PowerGridNodeBuilder extends BaseBuilder
{
	PropertyManagerService propertyManagerService;
	TiledPropertyManager propertyManager;

	public PowerGridNodeBuilder(RubyContext rubyContext) throws RubyDataServiceNotInitializedException
	{
		super(rubyContext);
		// TODO: dit is misschien handig om in de basebuilder te zetten
		propertyManagerService = RubyDataServiceAccessor.getInstance().getService(PropertyManagerService.class);
		propertyManager = propertyManagerService.getTiledPropertyManager();
	}


	private PowerRelayStationType getOrCreatePowerPlantType(List<TiledXmlProperty> properties) throws MapConvertException
	{
		PowerRelayStationType result =  null;
		String typeName = null;
		String responseTime = null;
		String powerplanttype = null;
		int capacity = 0;
		for (TiledXmlProperty property : properties)
		{
			switch (property.getType())
			{
			case  POWERPLANT_TYPENAME:
				typeName = property.getValue();
				break;
			case POWERPLANT_CAPACITY:
			case POWERPLANT_POWEROUTPUT:
				capacity =  Integer.valueOf(property.getValue());
				break;
			case POWERPLANT_REPSONSETIME:
				responseTime = property.getValue();
				break;
			case POWERPLANT_TYPE:
				powerplanttype = property.getValue();
				break;
			}
		}

		if(typeName != null)  {

			result = getExistingRubyObject(typeName,PowerRelayStationType.class);
			if(result == null) {
				result = this.rubyContext.createRubyModel(PowerRelayStationType.class);
				result.setCapacity(capacity);
				// TODO: deze property geldt alleen voor powerplants niet voor substations
				result.setResponseTime(responseTime);
				result.setPowerplantType(powerplanttype);
				result.setName(typeName);
			}
		}
		return result;

	}
	private boolean isSubstation(String value) {
		boolean result = false;

		if(!value.equals("powerplant")) {
			result = true;
		}
		return result;
	}
	public PowerGridNode createPowerGridNode(XmlTile xmlTile,Tile tile) throws MapConvertException
	{
		TiledXmlProperty tileTypeProperty = propertyManager.getTileProperty(TilePropertyType.POWERSTATION_TYPE, xmlTile.getGID());

		// Eerst moeten we zeker weten dat deze tile powerrelaystation bevat.. anders heeft het geen zin om een RubyModel aan te maken
		if(tileTypeProperty != null) {

			// TODO: We moeten iets doen met de territory coordinaten van de powerplants.. Deze moeten ergen geregistreerd worden
			List<TiledXmlProperty> properties = propertyManager.getObjectGroupProperties(tile.getXposition(), tile.getYposition());
			PowerRelayStation powerRelayStation =	this.rubyContext.createRubyModel(PowerRelayStation.class);
			PowerGridNode powerGridNode = this.rubyContext.createRubyModel(PowerGridNode.class);
			powerGridNode.setRelayStation(powerRelayStation);

			PowerRelayStationType powerRelayStationType = getOrCreatePowerPlantType(properties);
			 powerRelayStation.setPowerRelayStationType(powerRelayStationType);

			// code schrijven die check of dit grid deel uitmaakt van een object
				for(TiledXmlProperty property : properties) {
					switch (property.getType()) {

					case POWERPLANT_CONNECTIONS:
						// TODO: connections mapping toevoegen
						break;
					case POWERPLANT_NAME:
					case SUBSTATION_NAME:
							powerGridNode.setName(property.getValue());
						 	powerRelayStation.setName(property.getValue());
						break;
					}

				}

			return powerGridNode;

		}
		else {
			return null;
		}
	}
}
