package nationbuilder.lib.data.map.builders;

import java.util.HashMap;
import java.util.List;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.data.map.entities.NamedObject;
import nationbuilder.lib.data.map.mapservice.TilePropertyType;
import nationbuilder.lib.data.map.mapservice.TiledPropertyManager;
import nationbuilder.lib.data.map.mapservice.TiledXmlProperty;


/**
 * @author patrick.ekkel
 */
public class BaseBuilder
{
	RubyContext rubyContext;

	PropertyManagerService propertyManagerService;

	TiledPropertyManager propertyManager;
	public BaseBuilder(RubyContext rubyContext) throws RubyDataServiceNotInitializedException
	{
		this.rubyContext = rubyContext;
	;

		propertyManagerService = RubyDataServiceAccessor.getInstance().getService(PropertyManagerService.class);
		propertyManager = propertyManagerService.getTiledPropertyManager();
	}

	/**
	 * Gets all objects with a certain name out of the database.. only works if you implements equals that can handle String matching against objects
	 * @param name
	 * @return
	 */
	protected <T extends NamedObject & RubyModel> T getExistingRubyObject(String name,Class type)
	{
		List<T> types = this.rubyContext.getModels(type);

		for (T currentType : types)
		{
			NamedObject namedObject = currentType;
			if (name.equals(namedObject.getName()))
			{
				return currentType;
			}
		}

		return null;
	}

	protected HashMap<TilePropertyType, String> mapProperties(List<TiledXmlProperty> properties)
	{

		HashMap<TilePropertyType, String> result = new HashMap<>();

		for (TiledXmlProperty property : properties)
		{

			result.put(property.getType(), property.getValue());
		}
		return result;
	}

}
