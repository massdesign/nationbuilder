package nationbuilder.lib.data.map.xml;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class TiledXmlMapFactory {

	
	
	public XmlMap createTiledXmlMap(String filename)
	{
		XmlMap result = null;
		File fxmlFile = new File(filename);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		try {
			dBuilder = dbFactory.newDocumentBuilder(); 
			Document doc = dBuilder.parse(fxmlFile);
			doc.getDocumentElement().normalize();
			result = new XmlMap(doc);
			
		
		}
	 catch (SAXException e) {
		e.printStackTrace();
	}
    catch( IOException e)  {
        e.printStackTrace();
    }
	catch (ParserConfigurationException e) {
		e.printStackTrace();
	}
		
			
		return result;
	}
}
