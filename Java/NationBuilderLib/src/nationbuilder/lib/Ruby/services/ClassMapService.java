package nationbuilder.lib.Ruby.services;

import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.ClassMapFactory;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public class ClassMapService implements RubyDataService
{

	public ClassMap createClassMap(RubyModel model) {

		ClassMapFactory factory = new ClassMapFactory(model.getClass());
		return factory.createClassmap(model);
	}

}
