package nationbuilder.lib.data.map.converter;

import java.io.File;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.entities.MapImageFile;
import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.xml.XmlTileSet;

/**
 * @author patrick.ekkel
 */
public class ImageBuilder
{
	RubyContext rubyContext;
	MapMap map;
	public ImageBuilder(RubyContext context,MapMap map) {

		this.rubyContext = context;
		this.map = map;
	}


	public Image createImage(XmlTileSet xmlTileSet) {

		nationbuilder.lib.data.map.xml.Image xmlImage = xmlTileSet.getImage();

		Image result = rubyContext.createRubyModel(Image.class);
		MapImageFile mapImageFile = rubyContext.createRubyModel(MapImageFile.class);


		mapImageFile.setResource(new File(xmlImage.getFileLocation()));
		result.setImageFile(mapImageFile);
		result.setHeight(xmlImage.getHeight());
		result.setWidth(xmlImage.getWidth());

		result.setTileHeight(xmlTileSet.getTileHeight());
		result.setTileWidth(xmlTileSet.getTileWidth());
		result.setFirstGid(xmlTileSet.getFirstGid());
		result.setLastGid(xmlTileSet.getLastGid());

		result.setUrl("/upload/" + xmlImage.getName());
		result.setName(xmlImage.getName());

		// TODO: dit er nog uitslopen want dit is tijdelijk.. dit moet op een andere plek komen te staan
		this.map.addImage(result);

		return result;
	}

}
