package nationbuilder.lib.data.map.mapservice;

/**
 * @author patrick.ekkel
 */
public interface TiledXmlProperty
{
	String getValue();
	void setValue(String value);
	TilePropertyType getType();
	void setType(TilePropertyType type);
}

