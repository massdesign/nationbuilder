package nationbuilder.lib.data.map.converter;

import java.util.ArrayList;
import nationbuilder.lib.data.map.converter.converterobjects.ProcessedMap;
import nationbuilder.lib.data.map.xml.XmlLayer;
import nationbuilder.lib.data.map.xml.XmlTileset;
import nationbuilder.lib.data.map.xml.XmlMap;

/**
 * @author patrick.ekkel
 */
public class XmlMapBuilder
{
	private XmlTilesetBuilder tilesetBuilder;
	private ConverterContext converterContext;

	public XmlMapBuilder() {

		this.converterContext = new ConverterContext();
		this.tilesetBuilder = new XmlTilesetBuilder(converterContext);
	}

	public void build(XmlMap xmlMap) {
		// de basis is de <map>
		ArrayList<XmlTileset> tileSets = xmlMap.getTilesets();
		// loop door alle tilesets heen <tileset>


		//
		ProcessedMap processedMap = new ProcessedMap();

		processedMap.setTilewidth(xmlMap.getTileWidth());
		processedMap.setTileheight(xmlMap.getTileHeight());
		processedMap.setHeight(xmlMap.getHeight());
		processedMap.setWidth(xmlMap.getHeight());

		converterContext.setProcessedMap(processedMap);
		// Bouw de tilesets op waar de tile definities in staan
		for(XmlTileset tileSet : tileSets) {

			this.tilesetBuilder.build(tileSet,processedMap);
		}

		for(XmlLayer xmlLayer : xmlMap.getLayers()) {



		}



	}


}
