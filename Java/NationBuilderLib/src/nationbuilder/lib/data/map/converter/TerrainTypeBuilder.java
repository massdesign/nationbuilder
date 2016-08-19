package nationbuilder.lib.data.map.converter;

import java.util.List;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.orm.objectfactories.RubyObjectFactoryImpl;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.mapservice.TileProperty;
import nationbuilder.lib.data.map.mapservice.TilePropertyType;
import nationbuilder.lib.data.map.mapservice.TiledPropertyManager;
import nationbuilder.lib.data.map.mapservice.TiledXmlProperty;
import nationbuilder.lib.data.map.xml.XmlTile;

/**
 * @author patrick.ekkel
 */
public class TerrainTypeBuilder
{
	private RubyContext rubyContext;

	private RubyObjectFactory rubyObjectFactory;


	public TerrainTypeBuilder(RubyContext rubyContext) {
		this.rubyContext = rubyContext;
		this.rubyObjectFactory = rubyContext.createRubyObjectFacory(TerrainType.class,TerrainType[].class);

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
			TerrainType existingTerrainType = getExistingTerrainType(tileTypeProperty.getValue());

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

	private TerrainType getExistingTerrainType(String terrainTypeName) {
		List<TerrainType> terrainTypes = this.rubyContext.getModels(TerrainType.class);


		for(TerrainType terrainType : terrainTypes) {

			if(terrainTypeName.equals(terrainType.getName())) {
				return terrainType;
			}
		}

		return null;
	}

}
