package nationbuilder.lib.data.map.builders;

import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.mapservice.TilePropertyType;
import nationbuilder.lib.data.map.mapservice.TiledPropertyManager;
import nationbuilder.lib.data.map.mapservice.TiledXmlProperty;
import nationbuilder.lib.data.map.xml.XmlTile;

/**
 * @author patrick.ekkel
 */
public class TerrainTypeBuilder extends BaseBuilder
{

	public TerrainTypeBuilder(RubyContext rubyContext) throws RubyDataServiceNotInitializedException
	{
		super(rubyContext);
	}

	public TerrainType createTerraintype(XmlTile xmlTile) throws RubyDataServiceNotInitializedException
	{
		TerrainType result = null;

		PropertyManagerService propertyManagerService = RubyDataServiceAccessor.getInstance().getService(PropertyManagerService.class);
		TiledPropertyManager propertyManager = propertyManagerService.getTiledPropertyManager();
		TiledXmlProperty tileTypeProperty = propertyManager.getTileProperty(TilePropertyType.TILE_TYPE, xmlTile.getGID());
		if (tileTypeProperty != null)
		{
			result = rubyContext.createRubyModel(TerrainType.class);
			TerrainType existingTerrainType = getExistingRubyObject(tileTypeProperty.getValue(),TerrainType.class);

			if (existingTerrainType == null)
			{
				result.setName(tileTypeProperty.getValue());
			}
			else
			{
				result = existingTerrainType;
			}
		}
		return result;
	}



}
