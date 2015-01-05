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
    public static String LIST_TYPE = "list";

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
                else
                {
                    // TODO: lousy implementation.. also check what kind of annotations are present
                    if(field.getAnnotations().length > 0) {
                        if (fieldType.toLowerCase().equals(LIST_TYPE)) {
                            // TODO: implement list specific operations
                        } else {

                            // we have a hit and we should map this value in the row
                           // System.out.println("object name: " + field.getType().getSimpleName());
                            String field_id = field.getType().getSimpleName().toLowerCase() + "_id";
                            result.addEntry(field_id,"");
                        }
                    }
                    // if it is not string or int it must be object (at least for now)
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
