package nationbuilder.lib.Ruby.services;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;

/**
 * @author patrick.ekkel
 */
public class RubyDataServiceAccessor
{
	private static RubyDataServiceLoader instance = null;


	private static Class clazz;

	// TODO: callee protected toepassen..
	public static void setClazz(Class clazz) {

		if(RubyDataServiceAccessor.clazz != null) {
			throw new IllegalStateException("RubyDataService can only be set once.. ");
		}
		RubyDataServiceAccessor.clazz = clazz;
	}
	// TODO: deze manier van laden zorgt ervoor dat we geen twee contexts naast elkaar kunnen draaien, want dit is immers een "singleton"
	// NOTE: als de services geen staat bevatten is dit geen probleem natuurlijk
	public static  <T extends RubyDataServiceLoader> T getInstance() throws RubyDataServiceNotInitializedException
	{
		if (instance == null && clazz != null)
		{
			Constructor<?> ctor = null;
			try
			{
				ctor = clazz.getConstructor();
				instance= (T) ctor.newInstance();
				return (T)instance;
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
		else if(clazz == null) {
			throw new RubyDataServiceNotInitializedException("attempt to access getInstance because service is not yet loaded");
		}

		return (T)instance;
	}
}

