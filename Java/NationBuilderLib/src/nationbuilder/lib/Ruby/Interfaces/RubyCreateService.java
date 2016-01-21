package nationbuilder.lib.Ruby.Interfaces;

import java.io.IOException;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.data.map.ResponseData;

/**
 * Created by patrick on 12/12/14.
 */
public interface RubyCreateService
{
	ResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException;
	ResponseData postObject(Object objectToPost, String resourceUrl) throws ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException;
    void commit() throws RubyException;
}
