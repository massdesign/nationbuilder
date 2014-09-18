package nationbuilder.lib.Ruby.Exceptions;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * Created by patrick on 9/18/14.
 */
public class NotSavedEntityException extends Exception
{

	public NotSavedEntityException(RubyModel entity)
	{
		super("Trying to persist an object which contains an unsaved entity" + entity.toString());
	}
}
