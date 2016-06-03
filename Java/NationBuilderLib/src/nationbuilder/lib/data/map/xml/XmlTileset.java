package nationbuilder.lib.data.map.xml;

import java.util.ArrayList;

import nationbuilder.lib.data.map.entities.MapMap;
import org.w3c.dom.Element;

public class XmlTileset
{

    private MapMap parent;

	Element element;
	public XmlTileset(Element element)
	{
		this.element = element;
	}
	public int getFirstGid()
	{
		return XmlHelper.getInt(element, "firstgid");
	}
	public int getLastGid()
	{
		int result = 0;
		
		int width = this.getImage().getWidth();
		int height = this.getImage().getHeight();
		
		int columns = width/this.getTileWidth();
		int rows = height/this.getTileHeight();
		
		result = columns * rows;
		
		return result;
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
	public XmlImage getImage()
	{
		XmlImage result = null;
		ArrayList<Element> elements = XmlHelper.getElements("image", element);
		 if(elements.size() > 0)
		 {
			result = new XmlImage(elements.get(0));
		 }
		 return result;
    }

    public ArrayList<XmlTileDefinition> getTilesDefinitions()
    {
        ArrayList<XmlTileDefinition> result = new ArrayList<>();

        ArrayList<Element> elements = XmlHelper.getElements("tile",element);

        for(Element element : elements)
        {
            XmlTileDefinition tile = new XmlTileDefinition(element);
            result.add(tile);
        }
        return result;
    }


    public MapMap getParent() {
        return parent;
    }

    public void setParent(MapMap parent) {
        this.parent = parent;
    }
}
