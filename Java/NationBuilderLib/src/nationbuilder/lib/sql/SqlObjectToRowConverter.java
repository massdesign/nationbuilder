package nationbuilder.lib.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyPluralizer;

/**
 * Created by patrick on 12/19/14.
 */
public class SqlObjectToRowConverter
{
	public static String STRING_TYPE = "string";
	public static String INT_TYPE = "int";
    public static String LIST_TYPE = "list";

	public ObjectMap createObjectMap(RubyModel model) throws MissingAnnotationException {
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
                            Annotation annotation = null;
                            Field mappedField =   RubyAssociationResolver.getMappedField(field,currentClass);

                            Entity fieldEntityAnnotation = field.getType().getAnnotation(Entity.class);

                            if(fieldEntityAnnotation != null)
                            {
                                String field_id = RubyPluralizer.DePluralize(fieldEntityAnnotation.tableName()) + "_id";
                                String fieldValue =  (String)mappedField.get(model);
                                result.addEntry(field_id,fieldValue);
                            }
                            else
                            {
                               throw new MissingAnnotationException("missing Entity annotation on " + field.getType().getSimpleName());
                            }


                            // assuming the mappedField is always of type String


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
