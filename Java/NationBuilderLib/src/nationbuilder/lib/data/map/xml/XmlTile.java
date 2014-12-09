package nationbuilder.lib.data.map.xml;

import nationbuilder.lib.Logging.Log;
import org.w3c.dom.Element;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class XmlTile
{

	Element element;
	public XmlTile(Element element)
	{
		this.element = element;
	}



    public int getGID()
	{
        int result = -1;
        try {
            result = XmlHelper.getInt(this.element, "gid");

            return  result;
        }
        catch (NumberFormatException ex)
        {
            Log.writeInfo("gid attribute fetch failed, trying id attribute");
            // if we catch a numberformat exception.. fall be to the attribute id and see if if that one works

        }
        try
        {
            result =  XmlHelper.getInt(this.element,"id");
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

    @Override
    public String toString() {
       StringBuilder sb = new StringBuilder();
        for(Property property : getProperties())
        {
           sb.append(property.toString());
        }
        return "{Gid  " + this.getGID() +", Properties: " + sb.toString();
    }

}
