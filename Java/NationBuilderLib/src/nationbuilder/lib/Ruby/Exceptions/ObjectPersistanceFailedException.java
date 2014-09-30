package nationbuilder.lib.Ruby.Exceptions;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * Created by patrick on 9/18/14.
 */
public class ObjectPersistanceFailedException extends RubyException
{
	public ObjectPersistanceFailedException(RubyModel model,Exception ex)
	{
		super("Ruby Mapper failed to persist the following model" + model.toString() + "Exception: " + ex.getMessage());
	}


}
