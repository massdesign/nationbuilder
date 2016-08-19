package nationbuilder.lib.data.map.converter;

import java.util.List;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
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
public class PowerRelayStationBuilder
{
	RubyContext rubyContext;

	public PowerRelayStationBuilder(RubyContext rubyContext) {

		this.rubyContext = rubyContext;
	}

	public PowerRelayStation createPowerRelayStation(XmlTile xmlTile,Tile tile) throws RubyDataServiceNotInitializedException, MapConvertException
	{

		PropertyManagerService propertyManagerService = RubyDataServiceAccessor.getInstance().getService(PropertyManagerService.class);
		TiledPropertyManager propertyManager = propertyManagerService.getTiledPropertyManager();
		TiledXmlProperty tileTypeProperty = propertyManager.getTileProperty(TilePropertyType.POWERSTATION_TYPE, xmlTile.getGID());


		// Eerst moeten we zeker weten dat deze tile powerrelaystation bevat.. anders heeft het geen zin om een RubyModel aan te maken
		if(tileTypeProperty != null) {
		     PowerRelayStation result =	this.rubyContext.createRubyModel(PowerRelayStation.class);
			 PowerRelayStationType powerRelayStationType = this.rubyContext.createRubyModel(PowerRelayStationType.class);
			result.setPowerRelayStationType(powerRelayStationType);

			// code schrijven die check of dit grid deel uitmaakt van een object
			List<TiledXmlProperty> properties = propertyManager.getObjectGroupProperties(tile.getXposition(),tile.getYposition());
				for(TiledXmlProperty property : properties) {


					switch (property.getType()) {

					case POWERSTATION_TYPE:
						powerRelayStationType.setName(property.getValue());
						break;
					case POWERPLANT_CAPACITY:
						powerRelayStationType.setCapacity(Integer.valueOf(property.getValue()));
						break;
					case POWERPLANT_CONNECTIONS:
						break;
					case POWERPLANT_NAME:
					case SUBSTATION_NAME:
						result.setName(property.getValue());
						break;
					case POWERPLANT_TYPE:
						break;
					case POWERPLANT_REPSONSETIME:
						powerRelayStationType.setResponseTime(property.getValue());
					case POWERPLANT_POWEROUTPUT:
						break;
					}

				}

			return result;

		}
		else {
			return null;
		}
	}
}
