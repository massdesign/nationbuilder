package nationbuilder.lib.data.map.xml;

import org.w3c.dom.Element;

public class LayerFactory {

	
	
	public LayerFactory()
	{
		
	}
	public XmlLayer createLayer(Element element)
	{
		XmlLayer result = new XmlLayer(element);
		return result;
	}
}
