package nationbuilder.lib.data.map.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

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

	public ArrayList<TileSet> getTilesets()
	{
		ArrayList<Element> elements = XmlHelper.getElements("tileset",this.doc);
		ArrayList<TileSet> result = new ArrayList<TileSet>();
		for(Element element : elements)
		{
			result.add(new TileSet(element));
		}
		return result;
	}
	public ArrayList<Layer> getLayers()
	{
		ArrayList<Element> elements = XmlHelper.getElements("layer",this.doc);
		ArrayList<Layer> result = new ArrayList<Layer>();
		for(Element element : elements)
		{
			
			result.add(new Layer(element));
		}
		return result;
	} 
	
	
}
