package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.PostRequestFailedException;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.data.HttpResponseData;

import java.io.IOException;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyService {

    public HttpResponseData postObject(Object objectToPost,String resourceUrl,String rootValue) throws IOException;
    public HttpResponseData postObject(Object objectToPost,String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException;
    public int postFile(String fileLocation,String resourceUrl) throws IOException;
    public int postFile(BaseRubyResourceModel file,String resourceUrl) throws IOException;
	public HttpResponseData getObject(String resourceUrl);

}
