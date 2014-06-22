package nationbulder.map.xml;

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
	
	public BufferedImage getBufferedImage()
	{
		String hd = Configuration.ImageHomeDir;
		
		
		BufferedImage result = null;
	    String source =	element.getAttribute("source");
	    
	    File fn = new File(source);
	    
	    source = String.format("%s/%s", hd,fn.getName());
	    
		
	    try {
			result = ImageIO.read(new File(source));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return result;
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
