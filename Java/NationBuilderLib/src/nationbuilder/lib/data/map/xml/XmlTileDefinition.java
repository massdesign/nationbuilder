package nationbuilder.lib.data.map.xml;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.data.map.converter.converterobjects.PropertyList;
import org.w3c.dom.Element;

/**
 * @author patrick.ekkel
 */
public class XmlTileDefinition implements PropertyList
{
	private Element element;

	public XmlTileDefinition(Element element) {
		this.element = element;
	}


	public List<XmlProperty> getProperties()
	{
		ArrayList<XmlProperty> properties = new ArrayList<XmlProperty>();
		ArrayList<Element> elements = XmlHelper.getElements("properties", element);

		if (elements.size() > 0)
		{
			Element propertyElement = elements.get(0);

			for (Element element : XmlHelper.getElements("property", propertyElement))
			{

				XmlProperty property = new XmlProperty(element);
				properties.add(property);
			}
		}

		return properties;
	}

	public int getId() {

			int result = -1;
			result = XmlHelper.getInt(this.element, "id");
			// count +1 because tiled somehow keeps track of another way of counting when doing properties
			// result++;
			return result;
	}

}
