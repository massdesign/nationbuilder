package nationbuilder.lib.data.map.xml;

import nationbuilder.lib.Logging.Log;
import org.w3c.dom.Element;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class XmlTile
{
	private Element element;
	public XmlTile(Element element)
	{
		this.element = element;
	}



    public int getGID()
	{
        int result = -1;
            result = XmlHelper.getInt(this.element, "gid");
            return  result;
	}

}
