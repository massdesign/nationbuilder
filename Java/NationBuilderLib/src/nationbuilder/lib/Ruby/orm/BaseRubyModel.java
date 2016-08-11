package nationbuilder.lib.Ruby.orm;

import com.google.gson.annotations.Expose;
import java.lang.reflect.Field;
import java.util.Stack;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.Column;
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

    private boolean committed;
    private boolean isDirty;
    private String signature  = "";

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
    private String createSignature() {

        // get all columns..
        StringBuilder sb = new StringBuilder();
        for(Field field : this.getClass().getDeclaredFields()) {

            field.setAccessible(true);
            // alleen velden gemarkeerd als Column meenemen voor het genereren van de signature
            if(field.getAnnotation(Column.class) != null) {

                try
                {
                  Object result =  field.get(this);
                   if(result != null)
                   {
                       String hashCodeValue = String.valueOf(result.hashCode());
                       sb.append(hashCodeValue);
                   }
                    else {

                       sb.append("null");
                   }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }
            }
        }
        return sb.toString();
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

    @Override
    public void Save() throws RubyException
	{
        if (context == null)
        {
            throw new NoAttachedRubyContextException(this);
        }
        this.objectPersister.persistObject();
    }


    @Override
    public boolean isCommitted() {
        return this.committed;
    }

    @Override
    public void setCommitted(boolean committed) {
        this.committed = committed;
    }
    @Override
    public boolean markDirty()
    {
        // if the object is marked dirty we will be naive about it, if the object is not dirty we will ask questions
        if(!isDirty) {
            String newSignature = createSignature();
            this.setDirty(!signature.equals(newSignature));
            signature = newSignature;

        }
        return isDirty;
    }



    @Override
    public void setDirty(boolean dirty)
    {
        isDirty = dirty;
    }

}
