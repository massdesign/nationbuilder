package nationbuilder.lib.Ruby.Association;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.MappedBy;
import nationbuilder.lib.Ruby.Association.annotation.MappingInfo;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Exceptions.NotSavedEntityException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import nationbuilder.lib.Ruby.ID;
import nationbuilder.lib.Ruby.ReferenceMapping;

/**
 * Created by patrick on 7/23/14.
 */
public class RubyAssociationResolver
{
	public static void AssignIds(RubyModel model) throws NotSavedEntityException
	{

		if (model != null)
		{
            Class currentClass =  model.getClass();

            while(currentClass != null) {
                for (Field field : currentClass.getDeclaredFields()) {
                    Field mappedField = null;
                    field.setAccessible(true);
                    mappedField = getMappedField(OneToOne.class, field, currentClass);
                    if (mappedField != null) {
                        HandleOneToOneRelation(mappedField, model, field);
                        continue;
                    }
					mappedField = getMappedField(ManyToOne.class, field, currentClass);
					if(mappedField != null){
						HandleOneToOneRelation(mappedField, model, field);
						continue;

					}
                    mappedField = getMappedField(OneToMany.class, field, currentClass);
                    if (mappedField != null) {
                        HandleOneToManyRelation(mappedField, field, model);
                        continue;
                    }
                }
              currentClass =  currentClass.getSuperclass();
            }
		}
	}
	private static void HandleOneToManyRelation(Field mappedField,Field objectField,RubyModel model)
	{
		try
		{
			Object fieldValue = objectField.get(model);
			List<RubyModel> rubyModels = new ArrayList<RubyModel>();
			if(fieldValue instanceof Collection)
			{

			   Collection collection =	(Collection)fieldValue;
			   Object [] list =	collection.toArray();
				for(Object object : list)
				{
					if(object instanceof RubyModel)
					{
						rubyModels.add((RubyModel)object);
					}
				}

			}

		     mappedField.set(model,CreateIDsFromArrayList(rubyModels));
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
	}
	private static void HandleOneToOneRelation(Field mappedField,RubyModel model,Field objectField) throws NotSavedEntityException
	{		
		        try
		        {

                    Object fieldValue = objectField.get(model);
                    // NOTE: disabled the code responsable for coupling because we decided it would be unpractical to do it like this	
                    if (fieldValue instanceof RubyModel)
                    {
                        if (fieldValue != null)
                        {
                            RubyModel castedFieldValue = (RubyModel) fieldValue;
                            ID fieldId = castedFieldValue.getId();
                           // OneToOne annotation =  objectField.getAnnotation(OneToOne.class);
					        // if(annotation != null)
                             //{
					             //String mappedByVar =  annotation.mappedBy();
					            // Class refClazz = ReferenceMapping.class;
				            	// Constructor<?> ctor = refClazz.getConstructor();
					 
			                    // ReferenceMapping instance =  (ReferenceMapping)ctor.newInstance();
			         
			                   // instance.setID(fieldId);
			                   // instance.setClassType(model.getClass());
			                
			                    /*Field[] fieldFields =   fieldValue.getClass().getDeclaredFields();
			         
			                   for(Field field : fieldFields)
			                   {
			                  	 if(field.getName().equals(mappedByVar) && field.getType().getSimpleName().toLowerCase().equals("referencemapping"))
			                  	 {
			                  		 field.setAccessible(true);
			                  		 field.set(fieldValue, instance);
			        	        	 break;
			        	         }
			                   }
			                   */
				        	//}
					        if (fieldId != null)
				        	{
					        	mappedField.set(model, fieldId.getId());
				        	}
				        	else
				        	{
				        		throw  new NotSavedEntityException(castedFieldValue);
				        	}
			        	}
		        	}
                    else if(fieldValue instanceof ReferenceMapping)
                    {
                       // ReferenceMapping castedFieldValue = (ReferenceMapping)fieldValue;

                       // ID fieldId = castedFieldValue.getID();


                    }
		    }
		catch (IllegalAccessException e)
		{
			Log.write(e, LogType.ERROR);
		} 
		//catch (InstantiationException e) {
		//	Log.write(e, LogType.ERROR);
		//}
		 catch (IllegalArgumentException e) {
			Log.write(e, LogType.ERROR);
		 }
		//} catch (InvocationTargetException e) {
	//		Log.write(e, LogType.ERROR);
		//} catch (NoSuchMethodException e) {
	//		Log.write(e, LogType.ERROR);
		//}
		    catch (SecurityException e) {
			Log.write(e, LogType.ERROR);
		}
	}
    public static Field getMappedField(Field field,Class currentClass)
    {
        Field result = null;

        result = getMappedField(OneToOne.class, field, currentClass);
        if(result == null)
        {
            result =  getMappedField(ManyToOne.class, field, currentClass);
            if(result == null)
            {
                result = getMappedField(OneToMany.class, field, currentClass);
            }
        }

        return result;
    }
	public static MappingInfo getMappingInfo(Field field,RubyModel instance)
	{
		MappingInfo result = null;
	   Object o =  field.getAnnotation(OneToOne.class);
		if(o != null)
		{
			result = new MappingInfo(((OneToOne)o).mappedBy(),((OneToOne)o).mappedByClazz(),instance,field);
		}
		else
		{
		   o =	field.getAnnotation(OneToMany.class);
		   if(o != null)
		   {
			   result = new MappingInfo(((OneToMany) o).mappedBy(), ((OneToMany) o).mappedByClazz(), instance, field);
		   }
			else  {
			   o = field.getAnnotation(ManyToOne.class);
			   if(o != null)
			   {
				   result = new MappingInfo(((ManyToOne)o).mappedBy(), ((ManyToOne) o).mappedByClazz(),instance,field);
			   }
		   }

		}
		return result;
	}
	private static Field getMappedField(Class annotationType,Field field,Class currentClass)
	{
		Field result = null;
		if(field.isAnnotationPresent(annotationType))
		{
			Annotation annotationInstance = field.getAnnotation(annotationType);
			String fieldidentifier = "";
			//MappedBy mappedBy = MappedBy.SELF;
            // OneToOne and many to one relationships are threated as the same at the moment.. in the future this may change
			if(annotationInstance instanceof OneToOne)
			{
			  fieldidentifier =	((OneToOne)annotationInstance).mapIdTo();

			}
            else if(annotationInstance instanceof ManyToOne)
            {
                fieldidentifier = ((ManyToOne) annotationInstance).mapIdTo();
            }
			else if(annotationInstance instanceof OneToMany)
			{
				fieldidentifier =  ((OneToMany)annotationInstance).mapIdTo();
			}
			try
			{
                if(fieldidentifier.equals(MappedBy.SELF)) {
                    // Set ourselves as reference field
                    result = field;

                }
                else if(!fieldidentifier.equals(""))
                {
                    result = currentClass.getDeclaredField(fieldidentifier);
                }
                else
                {
                    // TODO: throw exception that annotation is wrong
                }

			}
			catch (NoSuchFieldException e)
			{
				Log.write(e, LogType.ERROR);
			}
			// if annotation is foreign we will ignore the property and return null

		}
		if(result != null)
		{
			result.setAccessible(true);
		}

		return result;
	}
	// NOTE: workaround to create something that works for now
	public static int[] CreateIDsFromArrayList(List<? extends RubyModel> list)
	{
		int[] result = new int[list.size()];
		int i = 0;
		for (RubyModel model : list)
		{
			if (model.getId() != null)
			{
				result[i] = Integer.parseInt(model.getId().getId());
				i++;
			}
		}
		return result;
	}

}
