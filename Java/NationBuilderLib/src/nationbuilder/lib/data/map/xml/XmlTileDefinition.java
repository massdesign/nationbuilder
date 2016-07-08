package nationbuilder.lib.data.map.xml;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Logging.Log;
import org.w3c.dom.Element;

/**
 * @author patrick.ekkel
 */
public class XmlTileDefinition
{

	Element element;


	public XmlTileDefinition(Element element) {
		this.element = element;
	}


	public List<Property> getProperties() {

		List<Property> result = new ArrayList<>();

		ArrayList<Element> elements = XmlHelper.getElements("properties", this.element);
		elements = XmlHelper.getElements("property", elements.get(0));

		for (Element element : elements)
		{
			result.add(new Property(element));
		}

		return result;
	}

	public int getId()
	{
		int result = -1;
		try
		{
			result = XmlHelper.getInt(this.element, "id");
			// count +1 because tiled somehow keeps track of another way of counting when doing properties
			// result++;
			return result;
		}
		catch (NumberFormatException ex)
		{
			Log.writeError("Parsing of the Tile GID attribute failed");
			return result;
		}
	}


	@Override
	public String toString()
	{
		return "XmlTileDefinition{" +
			   "element=" + element +
			   '}';
	}
}
