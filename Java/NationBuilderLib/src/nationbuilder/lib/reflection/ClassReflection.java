package nationbuilder.lib.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;

/**
 * @author patrick.ekkel
 */
public class ClassReflection
{
	/**
	 * Klasse die gemaakt moet worden, werkt alleen voor objecten met default constructor
	 * @param c
	 * @return
	 */
	public static Object createInstanceFromClassDef(Class c) {

		Object result = null;

		try
		{
			Class<?> clazz = null;
			clazz = Class.forName(c.getName());
			Constructor<?> ctor = clazz.getConstructor();
			result = ctor.newInstance();
		}
		catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException | InstantiationException | NoSuchMethodException e)
		{
			Log.write(e, LogType.ERROR);
		}

		return result;
	}

}
