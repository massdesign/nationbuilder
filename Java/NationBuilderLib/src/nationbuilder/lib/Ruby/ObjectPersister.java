package nationbuilder.lib.Ruby;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.services.ClassMapService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;

/**
 * @author patrick.ekkel
 */
public class ObjectPersister
{

	private RubyContext context;
	private RubyModel rubyModel;
	private ClassMap classMap;

	public ObjectPersister(RubyContext rubyContext,RubyModel  rubyModel) throws RubyDataServiceNotInitializedException
	{

		this.context = rubyContext;
		this.rubyModel =  rubyModel;
		ClassMapService classMapService = RubyDataServiceAccessor.getInstance().getService(ClassMapService.class);
		this.classMap = classMapService.createClassMap(rubyModel);
	}

	// TODO: Resource URL wegrefactoren omdat dit zowizo niet gaat werken met de nieuwe auto class Saver
	public void persistObject() throws RubyException
	{
		Log.write(this.rubyModel.toString(),LogType.DEBUG);
		// voordat we dit object op gaan slaan moeten alle onderliggende entiteiten op gaan slaan..

		if (RubyAssociationResolver.StrategyIsTablePerClass(rubyModel) && !context.getRubyService().ignoreClassMapInsertStrategy())
		{
			this.handleTablePerClassSave();
		}
		else
		{
			this.saveToContext();
		}
	}

	private void saveToContext() throws RubyException
	{
		// voordat we dit object gaan opslaan moeten we kijken of dit object dirty is
		if(this.rubyModel.markDirty())
		{
			// voordat we gaan opslaan moeten we eerst zeker weten dat de onderliggende objectstructuur ook  opgeslagen is
			if (persistObjectHierarchy())
			{
				// als dat gelukt is mogen we het root element saven
				context.SaveObject(classMap, rubyModel);
				this.rubyModel.setDirty(false);
			}
			else
			{
				//  throw exception dat opslaan niet mogelijk is vanwege een hierarchy probleem
			}
		}
	}


	private boolean persistObjectHierarchy() throws RubyException
	{
		Class currentClass = this.rubyModel.getClass();

		while(currentClass != BaseRubyModel.class)
		{
			Field[] fields = currentClass.getDeclaredFields();
			try
			{
				for (Field field : fields)
				{

					field.setAccessible(true);
					Object currentField = field.get(rubyModel);
					// Als het een databaseoject is willen we het natuurlijk opslaan
					if (currentField != null && currentField instanceof RubyModel)
					{

						ObjectPersister objectPersister = retrieveObjectpersisterForModel((RubyModel) currentField);
						objectPersister.persistObject();
					}
					// lijstjes willen we overheen lopen en individueel opslaan
					else if (currentField != null && currentField instanceof List)
					{

						ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
						Class<?> stringListClass = (Class<?>) stringListType.getActualTypeArguments()[0];
						// dit werkt niet
						if (RubyModel.class.isAssignableFrom(stringListClass))
						{
							for (RubyModel rubyModel : (List<RubyModel>) currentField)
							{
								ObjectPersister persister = retrieveObjectpersisterForModel(rubyModel);
								persister.persistObject();
							}
						}
					}

				}

				currentClass = currentClass.getSuperclass();
			}
			catch (IllegalAccessException e)
			{
				Log.write(e, LogType.ERROR);
			}

		}
		return true;
	}

	private ObjectPersister retrieveObjectpersisterForModel(RubyModel rubyModel) {

		ObjectPersister result = null;

		// get the BaseRubyModel class to retrieve the ObjectPersister
		// TODO: wederom afhankelijkheid naar BaseRubyModel


		Field[] fields =  BaseRubyModel.class.getDeclaredFields();

		for(Field field : fields) {

			try
			{
				field.setAccessible(true);
				Object fieldObject = field.get(rubyModel);
				if(fieldObject != null && fieldObject.getClass().equals(ObjectPersister.class)) {


					result = (ObjectPersister)fieldObject;
					break;

				}
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}

		}



		return result;
	}


	private void handleTablePerClassSave() throws RubyException
	{

		// TODO: afhankelijkheid met het object BaseRubyModel hier geintroduceerd
		classMap.reverseIterator();
		// voor we gaan besluiten om een tableperclass sitatie te handelen is het handig dat we vastgesteld hebben of dit object dirty is
		if (rubyModel.markDirty())
		{
			for (Class currentClassname : classMap)
			{
				if (!currentClassname.equals(BaseRubyModel.class) && !currentClassname.equals(Object.class))
				{
					Entity entity = (Entity) currentClassname.getAnnotation(Entity.class);
					if (entity != null)
					{
						if (entity.tableName() != null && !entity.tableName().equals(""))
						{
							this.saveToContext();
							// Het handelt zich hier om een meerdere tabellen per klasse, dat betekend dat we er vanuit mogen gaan dat 1 SaveToContext niet genoeg is om de dirty staat van het object op te heffen
							// we gaan er dus standaard vanuit dat dit object nog dirty is
							this.rubyModel.setDirty(true);
						}
						else
						{
							throw new IllegalArgumentException("tablename can't be null or empty");
						}

					}
					else
					{
						throw new MissingAnnotationException("Entity annotation expected on object: " + currentClassname.getClass());
					}
				}
			}
		}
	}

}
