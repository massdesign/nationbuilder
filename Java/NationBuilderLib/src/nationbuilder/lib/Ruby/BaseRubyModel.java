package nationbuilder.lib.Ruby;

import com.google.gson.annotations.Expose;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.RubyObjectHierarchy;
import nationbuilder.lib.Ruby.Exceptions.NoAttachedRubyContextException;
import nationbuilder.lib.Ruby.Exceptions.NotSavedEntityException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.ID;

import java.io.IOException;

/**
 * Created by patrick on 7/8/14.
 */
public class BaseRubyModel implements RubyModel {
    private ID id;
    @Expose
    protected RubyContext context;
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

    @Override
    public boolean Save(String ResourceUrl) throws RubyException
	{
		if (context == null)
		{
			throw new NoAttachedRubyContextException(this);
		}
        try {
           return context.SaveObject(this,ResourceUrl);
        }
		catch (RubyException e) {
            Log.write(ResourceUrl + " " + e.getMessage(), LogType.ERROR);
        }

        return false;
    }
}
