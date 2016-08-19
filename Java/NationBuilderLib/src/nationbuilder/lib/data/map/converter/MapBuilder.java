package nationbuilder.lib.data.map.converter;

import java.util.ArrayList;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Exceptions.ServiceAlreadyRegisteredException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.exceptions.MapConvertException;
import nationbuilder.lib.data.map.mapservice.TiledPropertyManager;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.XmlLayer;
import nationbuilder.lib.data.map.xml.XmlTileSet;

/**
 * @author patrick.ekkel
 */
public class MapBuilder
{
	private RubyContext rubyContext;
	private  ArrayList<Image> mapImages = new ArrayList<Image>();
	private LayerBuilder layerBuilder;

	// Tijdelijke oplossing
	private MapMap map;

	public MapBuilder(RubyContext rubyContext)
	{

		this.rubyContext = rubyContext;
	}

	public MapMap createMap(TiledXmlMap map) throws MapConvertException
	{
		MapMap resultMap = this.rubyContext.createRubyModel(MapMap.class);
		resultMap.setHeight(map.getHeight());
		resultMap.setWidth(map.getWidth());
		resultMap.setTileHeight(map.getTileHeight());
		resultMap.setTileWidth(map.getTileWidth());

		ImageBuilder imageBuilder = new ImageBuilder(this.rubyContext, resultMap);

		// TODO: hier  nog een andere constructie voor vinden..Nu liften we mee op de RubyDataAccessor
		try
		{   // Class registren als  service
			RubyDataServiceAccessor.getInstance().registerRubyService(PropertyManagerService.class);
			PropertyManagerService propertyManagerService  = RubyDataServiceAccessor.getInstance().getService(PropertyManagerService.class);
			propertyManagerService.setTiledPropertyManager(new TiledPropertyManager(map.getTilesets(),map));
		}
		catch (ServiceAlreadyRegisteredException | RubyDataServiceNotInitializedException e)
		{
			Log.write(e, LogType.ERROR);
		}

		for (XmlTileSet tileset : map.getTilesets())
		{
			mapImages.add(imageBuilder.createImage(tileset));
		}
		this.layerBuilder = new LayerBuilder(this.rubyContext, resultMap, mapImages);

		for (XmlLayer layer : map.getLayers())
		{
			try
			{
				resultMap.addLayer(layerBuilder.createLayer(layer));
			}
			catch (RubyDataServiceNotInitializedException e)
			{
				Log.write(e,LogType.ERROR);
			}
		}

		// NOTE: tijdelijke oplossing
		this.map = resultMap;
		return resultMap;

	}
	// Tijdelijke oplossing.. hier ga ik nog wat anders voor bedenken
	public void Persist() {

		try
		{
			this.map.Save();

		}
		catch(RubyException e)
		{
			e.printStackTrace();
		}



	}
}
