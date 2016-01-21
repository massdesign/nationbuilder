package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.data.map.ResponseData;

import java.io.IOException;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyService {

    public ResponseData postObject(Object objectToPost,String resourceUrl,String rootValue) throws IOException;
    public ResponseData postObject(Object objectToPost,String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException;
    public int postFile(String fileLocation,String resourceUrl) throws IOException;
    public int postFile(BaseRubyResourceModel file,String resourceUrl) throws IOException;
	public ResponseData getObject(String resourceUrl);
    public void commit() throws RubyException;

}
