package nationbuilder.lib.data.map.xml;

import org.w3c.dom.Element;

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
	
	
}
