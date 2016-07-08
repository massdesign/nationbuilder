package nationbuilder.lib.data.map.mapservice;

/**
 * @author patrick.ekkel
 */
public class IndexedProperty
{
	private String name;
	private String value;

	public IndexedProperty(String  name,String   value) {
		this.name  =  name;
		this.value =  value;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getValue()
	{
		return value;
	}

	public void setValue(String value)
	{
		this.value = value;
	}
}
