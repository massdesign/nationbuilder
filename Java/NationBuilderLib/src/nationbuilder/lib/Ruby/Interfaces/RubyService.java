package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.http.data.ResponseData;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyService {

    public ResponseData postObject(Object objectToPost,String resourceUrl,String rootValue) throws IOException;
    public ResponseData postObject(Object objectToPost,String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException;
    public int postFile(String fileLocation,String resourceUrl) throws IOException;
    public int postFile(BaseRubyResourceModel file,String resourceUrl) throws IOException;
	public ResponseData getObject(String resourceUrl);
    public void commit() throws RubyException;

}
