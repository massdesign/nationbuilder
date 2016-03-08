package nationbuilder.lib.Ruby.Association;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collection;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.MappedBy;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.NotSavedEntityException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

import java.lang.reflect.Field;
import java.util.List;

import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ID;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;
import nationbuilder.lib.reflection.ClassReflection;

/**
 * Created by patrick on 7/23/14.
 */
public class RubyAssociationResolver
{
	// TODO: voor al deze methoden kunnen we unit tests schrijven, dit moeten we doen als we klaar zijn met NB-13
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
                        // Hmm this does nothing.. consider getting rid of it?
                    }
		    }
		catch (IllegalAccessException e)
		{
			Log.write(e, LogType.ERROR);
		}
		 catch (IllegalArgumentException e) {
			Log.write(e, LogType.ERROR);
		 }
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
	public static Field getRefMappingField(Class clazz,String field_id) {

		Field result = null;
		try
		{
			// Eerst gaan we het proberen zonder id
			String stripped_field_id = field_id.replace("_id", "");
			result = clazz.getDeclaredField(stripped_field_id);
		}
		catch (NoSuchFieldException e)
		{
			// als we het niet zonder id kunnen vinden dan met id proberen
			try
			{
				result = clazz.getDeclaredField(field_id);
			}
			catch (NoSuchFieldException e1)
			{
				// alleen loggen verder niks doen
				Log.write(e1,LogType.ERROR);
			}

		}
		return result;
	}
	public static MappingInfo getMappingInfo(Field field,RubyModel instance)
	{
		MappingInfo result = null;
		if(field.getAnnotation(OneToOne.class) != null)  {
			OneToOne o = field.getAnnotation(OneToOne.class);
			result = new MappingInfo(o.mappedBy(), o.mappedByClazz(), instance, field,
					o.foreignKey());
			result.setMappingInfoType(MappingInfoType.OneToOne);


		}
		else  if(field.getAnnotation(OneToMany.class) != null) {
			OneToMany o = field.getAnnotation(OneToMany.class);
			result = new MappingInfo(o.mappedBy(), o.mappedByClazz(), instance, field, null);
			result.setMappingInfoType(MappingInfoType.OneToMany);

		}
		else if(field.getAnnotation(ManyToOne.class) != null) {
			ManyToOne o = field.getAnnotation(ManyToOne.class);
			result = new MappingInfo(o.mappedBy(), o.mappedByClazz(), instance, field, null);
			result.setMappingInfoType(MappingInfoType.ManyToOne);
		}
		else if(field.getAnnotation(nationbuilder.lib.Ruby.Association.annotation.ID.class) != null) {
			nationbuilder.lib.Ruby.Association.annotation.ID o = field.getAnnotation(
					nationbuilder.lib.Ruby.Association.annotation.ID.class);
			// TODO: mappedByClazz weggehaald, dit is niet consistent
			result = new MappingInfo(null,instance,field);
			result.setMappingInfoType(MappingInfoType.IDMapping);
		}
		// TODO: weggooien als bovenstaande code goed werkt
		/*
	   Object o =  field.getAnnotation(OneToOne.class);
		if(o != null)
		{

			result = new MappingInfo(((OneToOne)o).mappedBy(),((OneToOne)o).mappedByClazz(),instance,field,((OneToOne)o).foreignKey());
		}
		else
		{
		   o =	field.getAnnotation(OneToMany.class);
		   if(o != null)
		   {
			   result = new MappingInfo(((OneToMany) o).mappedBy(), ((OneToMany) o).mappedByClazz(), instance, field,null);
		   }
			else  {
			   o = field.getAnnotation(ManyToOne.class);
			   if(o != null)
			   {
				   result = new MappingInfo(((ManyToOne)o).mappedBy(), ((ManyToOne) o).mappedByClazz(),instance,field,null);
			   }
		   }

		}*/
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

	public static Field getIDFromSuperClass(ClassMap clazzMap,RubyModel baseRubyModel)  throws MissingAnnotationException
	{
		Field result = null;
		if(baseRubyModel != null) {

			Field [] fields = baseRubyModel.getClass().getSuperclass().getDeclaredFields();
			for(int i=0;i<fields.length;i++) {
				nationbuilder.lib.Ruby.Association.annotation.ID annotation =  fields[i].getAnnotation(nationbuilder.lib.Ruby.Association.annotation.ID.class);
				if(annotation != null) {
					// TODO: refactoren, dit is een beetje een fucked up constructie
				   Entity expectedEntity = ClassReflection.createInstanceFromClassDef(clazzMap.getSubClassFrom(clazzMap.getSuperClassFrom(baseRubyModel.getClass()))).getClass().getAnnotation(Entity.class);
				   Entity currentEntity = baseRubyModel.getClass().getAnnotation(Entity.class);


						if(expectedEntity != null) {

					   // we found the correct field for the correct class
					   if(expectedEntity.tableName().equals(currentEntity.tableName())) {
						   result = fields[i];
						   break;
					   }
					}
					else {
								throw new MissingAnnotationException("Expected Annotation Entity o " + expectedEntity.toString());

						}

					//else {
					//	}
				}

			}

		}
		return result;

	}
	public static boolean StrategyIsTablePerClass(RubyModel model) {
		boolean result = false;
		if(model.getClass().getAnnotations().length > 0) {

			Entity entity = model.getClass().getAnnotation(Entity.class);
			if(entity != null) {
				result = entity.strategy() == InhiritanceStrategy.TablePerClass;
			}
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
