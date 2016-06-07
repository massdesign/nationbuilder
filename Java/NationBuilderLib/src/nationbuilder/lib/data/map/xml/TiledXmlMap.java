package nationbuilder.lib.data.map.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class TiledXmlMap {

	private Document doc;
    private Element map;
	
	public TiledXmlMap(Document doc) {
		this.doc = doc;
        ArrayList<Element> elements =   XmlHelper.getElements("map",this.doc);
        if(elements.size() > 0)
        {
            this.map = elements.get(0);
        }
	}
	public TiledXmlMap()
	{
		
	}
	public void setDocument(Document doc) {
		this.doc = doc;
        ArrayList<Element> elements =   XmlHelper.getElements("map",this.doc);
        if(elements.size() > 0)
        {
           this.map = elements.get(0);

        }
	}


    public int getWidth() {

      return  XmlHelper.getInt(this.map,"width");
    }
    public int getHeight()
    {
        return  XmlHelper.getInt(this.map,"height");
    }
    public int getTileWidth()
    {
        return  XmlHelper.getInt(this.map,"tilewidth");
    }
    public int getTileHeight()
    {
        return XmlHelper.getInt(this.map,"tileheight");
    }

	public ArrayList<XmlTileSet> getTilesets()
	{
		ArrayList<Element> elements = XmlHelper.getElements("tileset",this.doc);
		ArrayList<XmlTileSet> result = new ArrayList<XmlTileSet>();
		for(Element element : elements)
		{
			result.add(new XmlTileSet(element));
		}
		return result;
	}
	public ArrayList<XmlLayer> getLayers()
	{
		ArrayList<Element> elements = XmlHelper.getElements("layer",this.doc);
		ArrayList<XmlLayer> result = new ArrayList<XmlLayer>();
		for(Element element : elements)
		{
			
			result.add(new XmlLayer(element));
		}
		return result;
	}
    public ArrayList<XmlObjectGroup> getObjectGroups() {

        ArrayList<Element> elements = XmlHelper.getElements("objectgroup",this.doc);

        ArrayList<XmlObjectGroup> result = new ArrayList<>();

        for(Element element : elements) {

            result.add(new XmlObjectGroup(element));
        }

        return result;

    }
	
	
}
