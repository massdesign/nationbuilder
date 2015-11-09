package nationbuilder.lib.Ruby.services;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.ServiceAlreadyRegisteredException;

/**
 * @author patrick.ekkel
 */
public class RubyDataServiceLoaderImpl<T extends RubyDataService> implements RubyDataServiceLoader
{
	List<RubyDataService> rubyServices;


	public RubyDataServiceLoaderImpl() {
		this.rubyServices = new ArrayList<>();
	}

	@Override
	public void registerRubyService(Class service) throws ServiceAlreadyRegisteredException
	{
		try
		{
			Constructor<?> ctor = service.getConstructor();
			RubyDataService instance = (RubyDataService) ctor.newInstance();
			if (!this.rubyServices.contains(instance))
			{
				this.rubyServices.add(instance);
			}
			else
			{
				throw new ServiceAlreadyRegisteredException("service: " + service.getSimpleName() + "is already registered");
			}
		}
		catch (NoSuchMethodException e)
		{
			Log.write(e, LogType.ERROR);
		}
		catch (InvocationTargetException e)
		{
			Log.write(e, LogType.ERROR);
		}
		catch (InstantiationException e)
		{
			Log.write(e, LogType.ERROR);
		}
		catch (IllegalAccessException e)
		{
			Log.write(e, LogType.ERROR);
		}
	}

	@Override
	public <A extends RubyDataService> A getService(Class service)
	{
		RubyDataService result = null;

		for(RubyDataService dataService : this.rubyServices) {
			if(dataService != null && dataService.getClass().getName().equals(service.getName())) {
				result = dataService;
				break;
			}
		}

		return (A)result;
	}
}




