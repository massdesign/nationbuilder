package nationbulder.map.xml;

import org.w3c.dom.Element;

public class LayerFactory {

	
	
	public LayerFactory()
	{
		
	}
	public Layer createLayer(Element element)
	{
		Layer result = new Layer(element);
		return result;
	}
}
