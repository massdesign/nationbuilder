package nationbuilder.lib.data.map.xml;

import org.w3c.dom.Element;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Tile {

	Element element;
	public Tile(Element element)
	{
		this.element = element;
	}
	
	public int getGID()
	{
	 return	XmlHelper.getInt(this.element, "gid");
	}


    public ArrayList<Property> getProperties()
    {
        ArrayList<Property> properties = new ArrayList<Property>();
        ArrayList<Element> elements = XmlHelper.getElements("properties",element);

        if(elements.size() > 0) {
            Element propertyElement = elements.get(0);

            for (Element element : XmlHelper.getElements("property", propertyElement)) {

                Property property = new Property(element);
                properties.add(property);
            }
        }

        return properties;
    }
	
}
