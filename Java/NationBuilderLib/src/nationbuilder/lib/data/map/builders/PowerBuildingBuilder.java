package nationbuilder.lib.data.map.builders;

import java.util.HashMap;
import java.util.List;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.data.map.entities.Building;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
import nationbuilder.lib.data.map.entities.EnergyBuildingType;
import nationbuilder.lib.data.map.entities.PowerGridComponent;
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
public class PowerBuildingBuilder extends BaseBuilder
{

	PowergridNodeBuilder powergridNodeBuilder;

	public PowerBuildingBuilder(RubyContext rubyContext) throws RubyDataServiceNotInitializedException
	{
		super(rubyContext);
		powergridNodeBuilder = new PowergridNodeBuilder(rubyContext);

	}

	private PowerGridComponent getOrCreatePowerGridComponent(HashMap<TilePropertyType,String> mappedProperties) throws MapConvertException
	{
		PowerGridComponent result = null;

		// Bepalen of we hier te maken met een powerplant of een substation
		String substationName =    mappedProperties.get(TilePropertyType.SUBSTATION_NAME);
		String powerplantName =    mappedProperties.get(TilePropertyType.POWERPLANT_NAME);
		// Dan is het een powerplant
		if(substationName == null && powerplantName != null ) {
			// TODO: dit weer opdelen in in een classe/builder/methodes
			// mapped van de powerplant entity
			EnergyBuilding intermittentResult = this.rubyContext.createRubyModel(EnergyBuilding.class);
		    String powerplantTypename = mappedProperties.get(TilePropertyType.POWERPLANT_TYPENAME);
			// Dit moet gevuld zijn anders keuren we de import af
			if(powerplantTypename == null)  {
				throw new  MapConvertException("powerplanttypename is null,on powerplant object");
			}

			EnergyBuildingType energyBuildingType = getExistingRubyObject(powerplantTypename,EnergyBuildingType.class);
			// Als hij niet bestaat moeten we hem maken
			if(energyBuildingType == null) {
				energyBuildingType = this.rubyContext.createRubyModel(EnergyBuildingType.class);
			}

			energyBuildingType.setResponsetime(mappedProperties.get(TilePropertyType.POWERPLANT_REPSONSETIME));
			energyBuildingType.setPowerOutput(Integer.valueOf(mappedProperties.get(TilePropertyType.POWERPLANT_POWEROUTPUT)));
			energyBuildingType.setEnergySource(mappedProperties.get(TilePropertyType.POWERPLANT_TYPE));
			energyBuildingType.setName(mappedProperties.get(TilePropertyType.POWERPLANT_TYPENAME));
			intermittentResult.setBuildingType(energyBuildingType);
			intermittentResult.setName(powerplantName);
			result = intermittentResult;


		}
		// Dan is het een substation
		else if(substationName != null && powerplantName == null) {

			PowerRelayStation intermittentResult = this.rubyContext.createRubyModel(PowerRelayStation.class);
			String substationTypename = mappedProperties.get(TilePropertyType.SUBSTATION_TYPENAME);

			if(substationTypename == null) {
				throw new MapConvertException("substationtypename is null on substationobject");
			}
			PowerRelayStationType powerRelayStationType = getExistingRubyObject(substationTypename,PowerRelayStationType.class);
			// Als hij niet bestaat moeten we hem maken
			if(powerRelayStationType == null) {
				powerRelayStationType = this.rubyContext.createRubyModel(PowerRelayStationType.class);
			}

			powerRelayStationType.setName(mappedProperties.get(TilePropertyType.SUBSTATION_TYPENAME));
			powerRelayStationType.setCapacity(Integer.valueOf(mappedProperties.get(TilePropertyType.SUBSTATION_CAPACITY)));


			intermittentResult.setPowerRelayStationType(powerRelayStationType);
			intermittentResult.setName(substationName);
			result = intermittentResult;
		}

		return  result;
	}
	public Building createPowerBuilding(XmlTile xmlTile,Tile tile) throws MapConvertException
	{
		PowerGridComponent result = null;
		TiledXmlProperty tileTypeProperty = propertyManager.getTileProperty(TilePropertyType.POWERSTATION_TYPE, xmlTile.getGID());

		// Eerst moeten we zeker weten dat deze tile powerrelaystation bevat.. anders heeft het geen zin om een RubyModel aan te maken
		if(tileTypeProperty != null) {

			// TODO: We moeten iets doen met de territory coordinaten van de powerplants.. Deze moeten ergen geregistreerd worden
			List<TiledXmlProperty> properties = propertyManager.getObjectGroupProperties(tile.getXposition(), tile.getYposition());
			HashMap<TilePropertyType, String> mappedProperties = mapProperties(properties);
			 PowerGridComponent powerGridComponent = getOrCreatePowerGridComponent(mappedProperties);
			// Dit is eigenlijk haast zeker, maar hiermee maken we expliciet
			if(powerGridComponent != null)
			{
				if (powerGridComponent instanceof Building)
				{

					Building building = (Building) powerGridComponent;
					PowerGridNode powerGridNode =  powergridNodeBuilder.createPowerGridNode(building);
					// bijhorende bij de building hoort ook een powergridnode
					result  = powerGridComponent;
				}
				else
				{
					throw new MapConvertException("powergridcomponent is niet van het type Building");
				}
			}


		}
		return (Building)result;
	}
}
