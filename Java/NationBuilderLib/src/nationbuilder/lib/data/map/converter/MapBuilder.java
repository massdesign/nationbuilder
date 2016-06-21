package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.xml.TiledXmlMap;

/**
 * @author patrick.ekkel
 */
public class MapBuilder
{
	private  RubyContext rubyContext;
	public MapBuilder(RubyContext rubyContext)  {

		this.rubyContext = rubyContext;
	}

	// TODO: MapBuilder nog verder invullen
	public MapMap createMap(TiledXmlMap map) {
		MapMap result = this.rubyContext.createRubyModel(MapMap.class);


		return result;
	}


}
