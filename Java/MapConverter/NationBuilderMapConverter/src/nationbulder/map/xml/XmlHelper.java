package nationbulder.map.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlHelper {

	public static ArrayList<Element> getElements(String elementName,Document doc)
	{
		ArrayList<Element> result = new ArrayList<Element>();
		  NodeList list =	doc.getElementsByTagName(elementName);
		  
		  for(int i=0;i<list.getLength();i++)
		  {
			  Node nNode = list.item(i);
			  
			 if(nNode.getNodeType() == Node.ELEMENT_NODE)
			 {
				 result.add((Element)nNode);			 }
		  }
		  return result;
	}
	public static ArrayList<Element> getElements(String elementName,Element element)
	{
		ArrayList<Element> result = new ArrayList<Element>();
		  NodeList list =	element.getElementsByTagName(elementName);
		  
		  for(int i=0;i<list.getLength();i++)
		  {
			  Node nNode = list.item(i);
			  
			 if(nNode.getNodeType() == Node.ELEMENT_NODE)
			 {
				 result.add((Element)nNode);
			 }
		  }
		  return result;
	}
	
	public static int getInt(Element element,String attributeName)
	{
		return Integer.parseInt(element.getAttribute(attributeName));
	}
	public static String getString(Element element,String attributeName)
	{
	   return element.getAttribute(attributeName);
	}
}
