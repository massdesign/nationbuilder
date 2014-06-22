package nationbulder.map.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TiledXmlMap {

	private Document doc;
	
	public TiledXmlMap(Document doc) {
		this.doc = doc;
	}
	public TiledXmlMap()
	{
		
	}
	public void setDocument(Document doc) {
		this.doc = doc;
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
