package nationbuilder.lib.data.map.xml;

import java.util.ArrayList;

import org.w3c.dom.Element;

public class Layer {
	
	Element element; 

	public Layer(Element element)
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
	
	public ArrayList<Tile> getTiles()
	{
		ArrayList<Tile> result = new ArrayList<Tile>();
		
		ArrayList<Element> elements = XmlHelper.getElements("data", this.element);
		
	    elements = XmlHelper.getElements("tile",elements.get(0));
	    
	    for(Element element : elements)
	    {
	    	result.add(new Tile(element));
	    }
		
		return result;
	}
	
	
}
