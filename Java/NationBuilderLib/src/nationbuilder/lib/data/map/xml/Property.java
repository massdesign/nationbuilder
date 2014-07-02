package nationbuilder.lib.data.map.xml;

import org.w3c.dom.Element;

/**
 * Created by patrick on 7/2/14.
 */
public class Property {

    Element element;

    public Property(Element element)
    {
     this.element = element;
    }
    public String getName() {
        return XmlHelper.getString(element, "name");
    }


    public String getValue() {
        return XmlHelper.getString(element, "value");
    }

    private String name;
    private String value;
}
