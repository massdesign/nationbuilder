package nationbuilder.lib.data.map.converter.converterobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author patrick.ekkel
 */
public class ProcessedTileDefinition
{
	private int id;
	private List<ProcessedProperty> properties;


	public ProcessedTileDefinition() {
		this.properties = new ArrayList<>();
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public List<ProcessedProperty> getProperties()
	{
		return properties;
	}
	public void addProperty(ProcessedProperty property) {
		this.properties.add(property);
	}
}
