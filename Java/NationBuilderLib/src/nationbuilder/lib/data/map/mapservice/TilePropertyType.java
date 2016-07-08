package nationbuilder.lib.data.map.mapservice;

/**
 * @author patrick.ekkel
 */
public enum TilePropertyType
{
	TILE_TYPE("tiletype"),CITY_TYPE("city_type"),MAX_POPULATION("max_population"),POWERSTATION_TYPE("powerstation_type");


	String  xmlName;
	TilePropertyType(String  xmlName) {
		this.xmlName =xmlName;
	}

}
