package nationbuilder.lib.Ruby.Interfaces;

import java.io.IOException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.http.data.HttpResponseData;

/**
 * Created by patrick on 12/12/14.
 */
public interface RubyCreateService
{
	HttpResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException;
	HttpResponseData postObject(Object objectToPost, String resourceUrl) throws ObjectPersistanceFailedException;
}
