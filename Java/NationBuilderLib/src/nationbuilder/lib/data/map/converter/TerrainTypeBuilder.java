package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.mapservice.TileProperty;
import nationbuilder.lib.data.map.mapservice.TilePropertyType;
import nationbuilder.lib.data.map.mapservice.TiledPropertyManager;
import nationbuilder.lib.data.map.xml.XmlTile;

/**
 * @author patrick.ekkel
 */
public class TerrainTypeBuilder
{

	private RubyContext rubyContext;
	public TerrainTypeBuilder(RubyContext rubyContext) {
		this.rubyContext = rubyContext;
	}

	public TerrainType createTerraintype(XmlTile xmlTile) throws RubyDataServiceNotInitializedException
	{
	   TerrainType result =	rubyContext.createRubyModel(TerrainType.class);

		PropertyManagerService propertyManagerService = RubyDataServiceAccessor.getInstance().getService(PropertyManagerService.class);
		TiledPropertyManager propertyManager = propertyManagerService.getTiledPropertyManager();
		TileProperty tileTypeProperty = propertyManager.getTileProperty(TilePropertyType.TILE_TYPE, xmlTile.getGID());


		if(tileTypeProperty != null)
		{
			result.setName(tileTypeProperty.getValue());
		}
		else {
			result = null;
		}

		return result;
	}
}
