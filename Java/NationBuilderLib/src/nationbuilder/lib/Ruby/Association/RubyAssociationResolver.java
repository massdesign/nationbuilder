package nationbuilder.lib.Ruby.Association;

import java.lang.reflect.Method;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import nationbuilder.lib.http.data.ID;

/**
 * Created by patrick on 7/23/14.
 */
public class RubyAssociationResolver
{
	// TODO: someday we will implement this stuff..
	public static RubyObjectHierarchy buildObjectTree(RubyModel model)
	{
		RubyObjectHierarchy result = new RubyObjectHierarchy();

		if (model != null)
		{
			for (Field field : model.getClass().getDeclaredFields())
			{
				field.setAccessible(true);
				if (field.isAnnotationPresent(OneToOne.class))
				{
					OneToOne annotationInstance = field.getAnnotation(OneToOne.class);
					String mappedField = annotationInstance.mapIdTo();

					try
					{

	          		    Field mappedFieldReference =	model.getClass().getDeclaredField(mappedField);
						mappedFieldReference.setAccessible(true);
						Object fieldValue = field.get(model);

						if(fieldValue instanceof RubyModel)
						{
						  if(fieldValue != null)
						  {
							  RubyModel castedFieldValue = (RubyModel) fieldValue;
							  ID fieldId =  castedFieldValue.getId();
							  if(fieldId != null)
							  {
								  mappedFieldReference.set(model, fieldId.getId());
							  }

						  }
						}


					}
					catch (NoSuchFieldException e)
					{
						// TODO: add error
					}
					catch (IllegalAccessException e)
					{
						e.printStackTrace();
					} System.out.println(mappedField);

				}
				else if (field.isAnnotationPresent(OneToMany.class))
				{

				}
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
