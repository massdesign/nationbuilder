package nationbuilder.lib.Ruby.Interfaces;

import java.io.IOException;

import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.data.map.ResponseData;

/**
 * Created by patrick on 12/12/14.
 */
public interface RubyCreateService
{
	ResponseData postObject(RubyModel objectToPost, String resourceUrl, String rootValue) throws IOException;
	ResponseData postObject(ClassMap clazzMap,RubyModel objectToPost, String resourceUrl) throws ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException;
    void commit() throws RubyException;
}
