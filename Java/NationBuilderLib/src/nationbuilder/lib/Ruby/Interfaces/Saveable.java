package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.Exceptions.NoAttachedRubyContextException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;

/**
 * Created by patrick on 7/8/14.
 */
public interface Saveable {

    public boolean Save(String resourceUrl) throws RubyException;
}
