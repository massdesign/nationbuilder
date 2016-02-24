package nationbuilder.lib.json.connectors;

import java.io.IOException;
import java.sql.SQLException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Exceptions.ColumnNotFoundException;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.PostRequestFailedException;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.http.HttpRequestUtil;
import nationbuilder.lib.http.data.HttpResponseData;

/**
 * Created by patrick on 12/12/14.
 */
public class JsonCreateServiceConnector implements RubyCreateService
{
	private String serverUrl;
	private ObjectBuilder objectBuilder;
	public JsonCreateServiceConnector(String serverUrl,ObjectBuilder objectBuilder)
	{
		this.serverUrl = serverUrl;
		this.objectBuilder = objectBuilder;
	}
	@Override
	public HttpResponseData postObject(RubyModel objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		// TODO: remove this method this one is not used
		return null;
	}
	@Override
	public HttpResponseData postObject(Class clazz,RubyModel objectToPost, String resourceUrl) throws PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException {
		String json = objectBuilder.createStringFromObject(objectToPost.getClass(),objectToPost);
		//	String json = gson.toJson(objectToPost);
		Log.writeInfo("Json object" + json.toString());
		HttpResponseData data = HttpRequestUtil.sendPostRequest(this.serverUrl + resourceUrl, json);
		return data;
	}

    @Override
    public void commit() {
        // Do nothing
    }
}
