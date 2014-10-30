package nationbuilder.lib.Ruby.Exceptions;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * Created by patrick on 9/30/14.
 */
public class RubyBackendConnectionFailed extends RubyException
{
	public RubyBackendConnectionFailed(Exception ex)
	{
		super("Java2Ruby was unable to setup a suitable connection between the client and the backend," + "internal message: " + ex.getMessage()) ;
	}

}
