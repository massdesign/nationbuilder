package nationbuilder.lib.Ruby.services;

import nationbuilder.lib.Ruby.Exceptions.ServiceAlreadyRegisteredException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public interface RubyDataServiceLoader
{
	public  void registerRubyService(Class service) throws ServiceAlreadyRegisteredException;
	public <A extends RubyDataService> A getService(Class service);

}
