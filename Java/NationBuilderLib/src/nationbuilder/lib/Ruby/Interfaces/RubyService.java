package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.data.HttpData;

import java.io.File;
import java.io.IOException;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyService {
    public HttpData postObject(Object objectToPost,String resourceUrl,String rootValue) throws IOException;
    public HttpData postObject(Object objectToPost,String resourceUrl) throws IOException;
    public int postFile(String fileLocation,String resourceUrl) throws IOException;
    public int postFile(BaseRubyResourceModel file,String resourceUrl) throws IOException;
	public HttpData getObject(String resourceUrl);

}
