package nationbuilder.lib.data.map.xml;

import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by patrick on 5/16/16.
 */
public class XmlObject {

    private Element element;

    public XmlObject(Element element) {
        this.element = element;
    }

    public String getId() {
        return XmlHelper.getString(element,"id");
    }
    public float getX() {
        return XmlHelper.getFloat(element,"x");
    }
    public float getY() {
        return XmlHelper.getFloat(element,"y");
    }
    public String width() {
        return XmlHelper.getString(element,"width");
    }
    public String height() {
        return XmlHelper.getString(element,"height");
    }
    public ArrayList<XmlProperty> getProperties() {
        // TODO: checken of dit goed is
        ArrayList<Element> elements = XmlHelper.getElements("property",XmlHelper.getElements("properties",element).get(0));
        ArrayList<XmlProperty> result = new ArrayList<>();

        for(Element currentElement : elements) {
          //  Element element1 = XmlHelper.getElements("property",currentElement).get(0);
            result.add(new XmlProperty(currentElement));
        }


        return result;
    }


}
