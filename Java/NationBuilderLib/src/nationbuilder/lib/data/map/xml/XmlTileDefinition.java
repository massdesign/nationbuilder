package nationbuilder.lib.data.map.xml;

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

	@Override
	public String toString()
	{
		return "XmlTileDefinition{" +
			   "element=" + element +
			   '}';
	}
}
