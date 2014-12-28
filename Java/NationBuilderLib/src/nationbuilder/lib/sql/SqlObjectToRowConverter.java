package nationbuilder.lib.sql;

import java.lang.reflect.Field;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * Created by patrick on 12/19/14.
 */
public class SqlObjectToRowConverter
{
	public static String STRING_TYPE = "string";
	public static String INT_TYPE = "int";

	public ObjectMap createObjectMap(RubyModel model)
	{
		ObjectMap result = new ObjectMap();

        result.addEntry("id","");
		Class currentClass = model.getClass();

		for(Field field : currentClass.getDeclaredFields())
		{

			try
			{
				String fieldType = field.getType().getSimpleName();
			    if(fieldType.toLowerCase().equals(STRING_TYPE) || fieldType.toLowerCase().equals(INT_TYPE))
				{
					field.setAccessible(true);
					result.addEntry(field.getName(), field.get(model));
				}
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
				//System.out.println(e)

			}

		}


		return result;
	}
}
