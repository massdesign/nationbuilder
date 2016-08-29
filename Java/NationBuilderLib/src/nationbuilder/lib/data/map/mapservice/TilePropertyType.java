package nationbuilder.lib.data.map.mapservice;

import nationbuilder.lib.data.map.exceptions.MapConvertException;

/**
 * @author patrick.ekkel
 */
public enum TilePropertyType
{
	TILE_TYPE("tiletype"),
	CITY_TYPE("city_type"),
	MAX_POPULATION("max_population"),
	POWERSTATION_TYPE("powerstation_type"),
	CITY_NAME("city_name"),
	CITY_POPULATION("city_population"),
	POWERPLANT_CAPACITY("powerplant_capacity"),
	POWERPLANT_CONNECTIONS("powerplant_connections"),
	POWERPLANT_NAME("powerplant_name"),
	POWERPLANT_REPSONSETIME("powerplant_responsetime"),
	POWERPLANT_TYPE("powerplant_type"),
	POWERPLANT_POWEROUTPUT("powerplant_poweroutput"),
	POWERPLANT_TYPENAME("powerplant_typename"),
	SUBSTATION_NAME("substation_name");

	String  xmlName;
	TilePropertyType(String  xmlName) {
		this.xmlName =xmlName;
	}


	static  TilePropertyType convertToEnum(String propertyname) throws MapConvertException
	{
		TilePropertyType result = null;
	   for(TilePropertyType type : TilePropertyType.values()) {

		   if(type.xmlName.equals(propertyname)) {
			   result = type;
			   break;
		   }

	   }
		if(result == null) throw new MapConvertException(propertyname + " not found in enummapping");

		return result;
	}

}
