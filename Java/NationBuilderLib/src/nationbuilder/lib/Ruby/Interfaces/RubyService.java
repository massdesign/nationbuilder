package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.data.map.ResponseData;

import java.io.IOException;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyService {

     ResponseData postObject(RubyModel objectToPost,String resourceUrl,String rootValue) throws IOException;
     ResponseData postObject(Class clazz,RubyModel objectToPost,String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException;
     int postFile(String fileLocation,String resourceUrl) throws IOException;
     int postFile(BaseRubyResourceModel file,String resourceUrl) throws IOException;
	 ResponseData getObject(String resourceUrl);
     boolean ignoreClassMapInsertStrategy();
     boolean ignoreClassMapSelectStrategy();
     void commit() throws RubyException;

}
