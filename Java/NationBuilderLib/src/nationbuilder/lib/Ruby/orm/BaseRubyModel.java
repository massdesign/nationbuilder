package nationbuilder.lib.Ruby.orm;

import com.google.gson.annotations.Expose;
import java.util.Stack;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.IgnoreInRails;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.NoAttachedRubyContextException;
import nationbuilder.lib.Ruby.Exceptions.NotSavedEntityException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyRESTHelper;

/**
 * Created by patrick on 7/8/14.
 */
public class BaseRubyModel implements RubyModel {

	@IgnoreInRails
    @nationbuilder.lib.Ruby.Association.annotation.ID(mapIdToEntity = BaseRubyModel.class)
    private ID id;

    @Expose
	@IgnoreInRails
    protected RubyContext context;

    boolean committed;

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
    }

    protected boolean Save(Class clazz,String resourceUrl) throws RubyException
    {
        return context.SaveObject(clazz,this, resourceUrl);
    }


    private void handleTablePerClassSave(Class currentClassname) throws RubyException
    {
        Stack<Class> classes = new Stack<>();
        // TODO: afhankelijkheid met het object BaseRubyModel hier geintroduceerd
        while (currentClassname != null && currentClassname.getName() != "nationbuilder.lib.Ruby.orm.BaseRubyModel")
        {
            classes.push(currentClassname);
            currentClassname = currentClassname.getSuperclass();
        }

        while (!classes.empty())
        {
            currentClassname = classes.pop();

            //  while (currentClassname != null && currentClassname.getName() != "nationbuilder.lib.Ruby.orm.BaseRubyModel")
            //  {

            try
            {
                Class objectInstance = this.getClass().forName(currentClassname.getName());

                //Object downCast =   objectInstance.
                Entity entity = (Entity) currentClassname.getAnnotation(Entity.class);
                if (entity != null)
                {

                    if (entity.tableName() != null && !entity.tableName().equals(""))
                    {

                        this.Save(objectInstance, entity.tableName());
                    }
                    else
                    {
                        throw new IllegalArgumentException("tablename can't be null or empty");
                    }
                }
                else
                {
                    throw new MissingAnnotationException("Entity annotation expected on object: " + objectInstance.getClass());
                }
            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }
        }
          //  currentClassname = currentClassname.getSuperclass();

       // }

    }
    @Override
    public boolean Save(String ResourceUrl) throws RubyException
	{
		if (context == null)
		{
			throw new NoAttachedRubyContextException(this);
		}


            if(RubyAssociationResolver.StrategyIsTablePerClass(this))
            {
                handleTablePerClassSave(this.getClass());

            }
            else
            {
                try
                    {
                    return context.SaveObject(this.getClass(),this, ResourceUrl);
                }
                catch (RubyException e)
                {
                    Log.write(ResourceUrl + " " + e.getMessage(), LogType.ERROR);
                }
            }
        return false;
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
