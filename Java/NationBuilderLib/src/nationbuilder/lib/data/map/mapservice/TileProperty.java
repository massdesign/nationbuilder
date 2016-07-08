package nationbuilder.lib.data.map.mapservice;

/**
 * @author patrick.ekkel
 */
public class TileProperty
{

	private  TilePropertyType type;
	private String value;

	public   TileProperty(TilePropertyType type,String value) {

		this.type   =  type;
		this.value =   value;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}

	public TilePropertyType getType()
	{
		return type;
	}

	public void setType(TilePropertyType type)
	{
		this.type = type;
	}
}
