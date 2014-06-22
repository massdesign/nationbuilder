package nationbuilder.lib.data.map.xml;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.w3c.dom.Element;

public class Image {

	private Element element;	
	public Image(Element element)
	
	{
		this.element = element;
	}
	
	public String getFileLocation()
	{
		String hd = Configuration.ImageHomeDir;
	    String source =	element.getAttribute("source");
	    
	    File fn = new File(source);    
	    source = String.format("%s/%s", hd,fn.getName());
		return source;
	}
	public int getWidth()
	{
		return XmlHelper.getInt(element, "width");
	}
	public int getHeight()
	{
		return XmlHelper.getInt(element, "height");
	}
	public String getName()
	{
		String source = element.getAttribute("source");
		File fn = new File(source);
		
		return fn.getName();
		
	}
	
	
}
