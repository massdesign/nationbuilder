package nationbuilder.lib.sql;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
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

    private List<String> systemFields;

    public SqlObjectToRowConverter()
    {
        // TODO: dit op een centrale plek regelen als het een keer nodig moet zijn
        this.systemFields  = new ArrayList<>();

        this.systemFields.add("rubycontext");
    }

    private boolean isSystemfield(Field field)
    {
        boolean result = false;
        if(field != null)
        {
            for (String s : this.systemFields)
            {
                if (s.equals(field.getType().getSimpleName().toLowerCase()))
                {
                    result = true;
                }
            }
        }
        return result;
    }

    private HashMap<Class,Field[]> getAllFieldsFromInstance(RubyModel rubyModel)
    {
        HashMap<Class, Field[]> fieldMap = new HashMap<>();

        if(rubyModel != null)
        {
            Class currentClass = rubyModel.getClass();

            while(currentClass != null)
            {
                fieldMap.put(currentClass, currentClass.getDeclaredFields());
                 currentClass = currentClass.getSuperclass();
            }
        }
        return fieldMap;
    }


    private ObjectMap.ObjectMapRow createObjectMapKV(Field field,Class currentClass,RubyModel model,ObjectMap om) throws IllegalAccessException, MissingAnnotationException
    {
        ObjectMap.ObjectMapRow result = null;
        if(!isSystemfield(field))
        {

            // we have a hit and we should map this value in the row
            // System.out.println("object name: " + field.getType().getSimpleName());
            Field mappedField = RubyAssociationResolver.getMappedField(field, currentClass);
            field.setAccessible(true);
            Object fieldDereferencedValue = field.get(model);
            if (fieldDereferencedValue != null)
            {
            //    fieldDereferencedValue.getClass().getAnnotation(Entity.class);

                Entity fieldEntityAnnotation = fieldDereferencedValue.getClass().getAnnotation(Entity.class);

                if (fieldEntityAnnotation != null)
                {
                    String field_id = RubyPluralizer.DePluralize(fieldEntityAnnotation.tableName()) + "_id";
                    String fieldValue = (String) mappedField.get(model);
                    result = om.createObjectMapRow(field_id, fieldValue);
                }
                else
                {
                    field.setAccessible(true);
                    Object fieldValue = field.get(model);
                    if (fieldValue != null)
                    {
                        fieldValue.getClass().getAnnotation(Entity.class);

                    }
                    throw new MissingAnnotationException("missing Entity annotation on field " + field.getType().getSimpleName());
                }
            }
        }
        return result;
    }

	public ObjectMap createObjectMap(RubyModel model) throws MissingAnnotationException {

		ObjectMap result = new ObjectMap();
        result.addEntry(result.createObjectMapRow("id",""));
        HashMap<Class,Field[]> fcMap = getAllFieldsFromInstance(model);
        Iterator it = fcMap.entrySet().iterator();
        while(it.hasNext())
        {
            Map.Entry pairs = (Map.Entry)it.next();

            for (Field field : (Field[])pairs.getValue())
            {

                try
                {

                    String fieldType = field.getType().getSimpleName();
                    if (fieldType.toLowerCase().equals(STRING_TYPE) || fieldType.toLowerCase().equals(INT_TYPE))
                    {
                        field.setAccessible(true);
                        result.addEntry(result.createObjectMapRow(field.getName(),field.get(model)));
                     //   result.addEntry(field.getName(), field.get(model));
                    }
                    else
                    {
                        // TODO: lousy implementation.. also check what kind of annotations are present
                        if (field.getAnnotations().length > 0)
                        {
                            if (fieldType.toLowerCase().equals(LIST_TYPE))
                            {
                                // TODO: implement list specific operations
                            }
                            else
                            {
                                ObjectMap.ObjectMapRow newObjectMapRow  = this.createObjectMapKV(field, (Class)pairs.getKey(), model, result);
                                if(newObjectMapRow != null)
                                {
                                    result.addEntry(newObjectMapRow);
                                }
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
        }


		return result;
	}
}
