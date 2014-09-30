package nationbuilder.lib.Ruby.Exceptions;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * Created by patrick on 9/30/14.
 */
public class NoAttachedRubyContextException extends RubyException
{
	public NoAttachedRubyContextException(RubyModel entity)
	{
		super("Trying to persist an object which is not attached to a specific ruby context" + entity.toString());
	}
}
