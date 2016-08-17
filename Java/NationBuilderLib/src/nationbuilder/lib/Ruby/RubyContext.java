package nationbuilder.lib.Ruby;

import java.net.ConnectException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.EntityType;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyBackendConnectionFailed;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.orm.objectfactories.RubyObjectFactoryImpl;
import nationbuilder.lib.Ruby.services.ClassMapService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

/**
 * Created by patrick on 7/8/14.
 */
public class RubyContext {

    public RubyService getRubyService() {
        return rubyService;
    }
    //Gson gson = new Gson();
    public void setRubyService(RubyService rubyService) {
        this.rubyService = rubyService;
    }
    private Class applicationContext;
    private RubyService rubyService;
    private RubyObjectManager rubyObjectMarshaller;

   // private RubyFetchService fetchService;
   // private RubyCreateService createService;
    private RubyStore rubyStore;
    private ObjectBuilder objectBuilder;
    public RubyContext(RubyService service,ObjectBuilder objectBuilder)
    {
     this.rubyService = service;
      // NOTE: maybe in the future we want to have this passed trough the constructor
     this.rubyStore = new RubyStore();
     this.objectBuilder = objectBuilder;
     this.rubyObjectMarshaller = new RubyObjectManager(this.getRubyService(),this.rubyStore,objectBuilder);
    }

    public<T extends RubyModel> T createRubyModel(Class<?> clazz)
    {
        try {
            Constructor<?> ctor = clazz.getConstructor();
            T instance =  (T)ctor.newInstance();
           instance.setRubyContext(this);
           this.rubyStore.registerRubyModel(instance);

            return instance;

        } catch (NoSuchMethodException e) {
            Log.write(e, LogType.ERROR);
        } catch (InvocationTargetException e) {
            Log.write(e,LogType.ERROR);
        } catch (InstantiationException e) {
            Log.write(e,LogType.ERROR);
        } catch (IllegalAccessException e) {
            Log.write(e,LogType.ERROR);
        }
        return null;
    }


	// TODO: deze constructor moet nog een keer anders.. het is lelijk om 2 keer hetzelfde te moeten definieren
	public <T extends RubyModel> RubyObjectFactory createRubyObjectFacory(Class<T> clazz,Class<?> clazzArray)
	{
			RubyObjectFactory result = new RubyObjectFactoryImpl<T>(clazz,clazzArray);
			result.setRubyContext(this);
			return result;
	}
    private boolean SaveObject(ModelPayload modelPayload,String resourceUrl) throws RubyException
	{
		modelPayload.getRubyModel().FetchIDs();
		try
		{
           return  this.rubyObjectMarshaller.store(modelPayload, resourceUrl);
		}
		catch (ConnectException e)
		{
			throw new RubyBackendConnectionFailed(e);
		}
		catch (IOException e)
		{
			throw new ObjectPersistanceFailedException(modelPayload.getRubyModel(),e);
		}


    }

    public boolean SaveObject(ModelPayload modelPayload) throws RubyException
    {
        boolean result;

        ClassMap clazzMap = modelPayload.getClassMap();
        RubyModel object = modelPayload.getRubyModel();

        // bij een singletableperinstance maken we geen gebruik van de clazzmap
        Entity entityMetaInfo = null;

        if(modelPayload.getInhiritanceStrategy() == InhiritanceStrategy.TablePerClass) {
            entityMetaInfo =  (Entity)clazzMap.getCurrent().getAnnotation(Entity.class);
        }
        else {
           entityMetaInfo = modelPayload.getRubyModel().getClass().getAnnotation(Entity.class);
        }

        if(entityMetaInfo != null && !entityMetaInfo.tableName().isEmpty())
        {
           result = this.SaveObject(modelPayload,entityMetaInfo.tableName());
        }
        else if(entityMetaInfo != null && entityMetaInfo.type() == EntityType.Resource) {
            result = true;
            // do nothing
            // TODO: implement files
            // cast this to a BaseRubyResourceModel

            BaseRubyResourceModel rubyResourceModel  = (BaseRubyResourceModel)object;
            rubyResourceModel.Save();

        }
        else {
            throw new MissingAnnotationException("Entity annotation is not set on" + object);
        }

        return result;
    }

    public void registerApplicationContext(Class applicationContext) {
        this.applicationContext = applicationContext;
    }


    public void commit() throws RubyException {
        this.rubyService.commit();
    }
    public boolean SaveResource(BaseRubyResourceModel object)
    {
        try {
            // TODO: dit moeten we nog een keertje instelbaar maken als we RubyDI voor meerdere toepassingen gaan gebruiken
            int fileStatusCode = this.rubyService.postFile(object, "/uploads/");
            return fileStatusCode != 200 ? false : true;
        } catch (IOException e) {
            Log.write(e,LogType.ERROR);
            return  false;
        }
    }
    public<T extends RubyModel> List<T> getModels(Class<?> clazz)
    {
        return this.rubyStore.getRubyModelByType(clazz);
    }

    public RubyObjectManager createRubyMarshaller() {

        return new RubyObjectManager(this.getRubyService(),this.rubyStore,this.objectBuilder);
    }
    public RubyObjectManager createRubyMarshaller(ObjectBuilder builder) {
        return new RubyObjectManager(this.getRubyService(),this.rubyStore,builder);
    }

    public Class getApplicationContext()
    {
        return applicationContext;
    }
}
