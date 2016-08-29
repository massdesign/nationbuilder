package nationbuilder.lib.data.map.builders;

import java.util.List;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.NamedObject;


/**
 * @author patrick.ekkel
 */
public class BaseBuilder
{
	RubyContext rubyContext;
	public BaseBuilder(RubyContext rubyContext) {
		this.rubyContext = rubyContext;
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

}
