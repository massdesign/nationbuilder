package nationbuilder.lib.data.map.xml;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class XmlLayer
{
	
	Element element; 

	public XmlLayer(Element element)
	{
		this.element = element;
	}
	
	public String getName()
	{
		return this.element.getAttribute("name");
	}
	public int getWidth()
	{
		return Integer.parseInt(this.element.getAttribute("width"));
	}
	public int getHeight()
	{
		return Integer.parseInt(this.element.getAttribute("height"));
	}
	
	public ArrayList<XmlTile> getTiles()
	{
		ArrayList<XmlTile> result = new ArrayList<XmlTile>();
		
		ArrayList<Element> elements = XmlHelper.getElements("data", this.element);
		
	    elements = XmlHelper.getElements("tile",elements.get(0));
	    
	    for(Element element : elements)
	    {
	    	result.add(new XmlTile(element));
	    }
		
		return result;
	}
	
	
}
