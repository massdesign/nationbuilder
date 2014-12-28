package nationbuilder.lib.Ruby.Interfaces;

import java.io.IOException;
import java.sql.SQLException;

import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.PostRequestFailedException;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.http.data.ResponseData;

/**
 * Created by patrick on 12/12/14.
 */
public interface RubyCreateService
{
	ResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException;
	ResponseData postObject(Object objectToPost, String resourceUrl) throws ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException;
}
