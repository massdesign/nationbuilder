package nationbuilder.lib.Ruby.orm;

import com.google.gson.annotations.Expose;
import java.util.Stack;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.IgnoreInRails;
import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.NoAttachedRubyContextException;
import nationbuilder.lib.Ruby.Exceptions.NotSavedEntityException;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.ObjectPersister;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.services.ClassMapService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;

/**
 * Created by patrick on 7/8/14.
 */
public class BaseRubyModel implements RubyModel {

	@IgnoreInRails
    @nationbuilder.lib.Ruby.Association.annotation.ID()
    private ID id;

    @Expose
	@IgnoreInRails
    protected RubyContext context;

    boolean committed;

    @Expose
    @IgnoreInRails
    private ObjectPersister  objectPersister;

    @Override
    public ID getId() {
        return id;
    }


    @Override
    public void setId(ID id) {
    this.id = id;
    }


	@Override
	public void FetchIDs()
	{
		try
		{
			RubyAssociationResolver.AssignIds(this);
		}
		catch (NotSavedEntityException e)
		{
			Log.write(e,LogType.ERROR);
		}
	}

	@Override
    public void setRubyContext(RubyContext context) {
        this.context = context;

        if (this.objectPersister == null)
        {
            try
            {
                this.objectPersister = new ObjectPersister(this.context, this);
            }
            catch (RubyDataServiceNotInitializedException e)
            {
                Log.write(e,LogType.ERROR);
            }
        }
    }

   // protected boolean Save(ClassMap clazzMap,String resourceUrl) throws RubyException
    //{
     //   return context.SaveObject(clazzMap,this, resourceUrl);
    //}


    /*private void handleTablePerClassSave(ClassMap classMap) throws RubyException
    {
       // Stack<Class> classes = new Stack<>();
        // TODO: afhankelijkheid met het object BaseRubyModel hier geintroduceerd
        classMap.reverseIterator();
        for(Class currentClassname: classMap)
        {

            if(!currentClassname.equals(BaseRubyModel.class) && !currentClassname.equals(Object.class)) {
                    // Class objectInstance = this.getClass().forName(currentClassname.getName());
                    Entity entity = (Entity) currentClassname.getAnnotation(Entity.class);
                    if (entity != null)
                    {
                        if (entity.tableName() != null && !entity.tableName().equals(""))
                        {
                            // the current class can be extracted from the classmap structure
                            this.Save(classMap, entity.tableName());
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
    }*/
    @Override
    public void Save() throws RubyException
	{
        if (context == null)
        {
            throw new NoAttachedRubyContextException(this);
        }

        this.objectPersister.persistObject();


		/*if (context == null)
		{
			throw new NoAttachedRubyContextException(this);
		}

       if(RubyAssociationResolver.StrategyIsTablePerClass(this) && !context.getRubyService().ignoreClassMapInsertStrategy())
           {
                ClassMapService classMapService = RubyDataServiceAccessor.getInstance().getService(ClassMapService.class);
                ClassMap classMap = classMapService.createClassMap(this);

                handleTablePerClassSave(classMap);
            }
        else
            {
                try
                    {
                     ClassMapService classMapService =   RubyDataServiceAccessor.getInstance().getService(ClassMapService.class);
                     ClassMap classMap = classMapService.createClassMap(this);
                     classMap.loadDefault();
                    return context.SaveObject(classMap,this, ResourceUrl);
                }
                catch (RubyException e)
                {
                    Log.write(ResourceUrl + " " + e.getMessage(), LogType.ERROR);
                }
            }
        return false;
        */
    }


    @Override
    public boolean isCommitted() {
        return this.committed;
    }

    @Override
    public void setCommitted(boolean committed) {
        this.committed = committed;
    }

}
