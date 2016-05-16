package nationbuilder.lib.data.map.xml;

import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by patrick on 5/16/16.
 */
public class XmlObjectGroup {

    Element element;

    public XmlObjectGroup(Element element) {

        this.element = element;
    }

    public ArrayList<XmlObject> getObjects() {
        ArrayList<XmlObject> result =  new ArrayList<>();
        ArrayList<Element> elements = XmlHelper.getElements("object",element);


        for (Element currentElement : elements) {
            result.add(new XmlObject(currentElement));

        }
        return result;
    }

}
