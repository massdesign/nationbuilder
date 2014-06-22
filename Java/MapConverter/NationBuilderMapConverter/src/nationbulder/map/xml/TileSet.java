package nationbulder.map.xml;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class TileSet {
	
	Element element;
	public TileSet(Element element)
	{
		this.element = element;
	}
	public int getFirstGid()
	{
		return XmlHelper.getInt(element, "firstgid");
	}
	public String getName()
	{
		
		
		return XmlHelper.getString(element, "name");
	}
	public int getTileWidth()
	{
		return XmlHelper.getInt(element, "tilewidth");
	}
	public int getTileHeight()
	{
		return XmlHelper.getInt(element, "tileheight");
	}
	public Image getImage()
	{
		Image result = null;
		ArrayList<Element> elements = XmlHelper.getElements("image", element);
		 if(elements.size() > 0)
		 {
			result = new Image(elements.get(0));
		 }
		 return result;
}
	

}
