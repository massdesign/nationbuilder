package nationbuilder.lib.http;

import java.io.IOException;
import java.sql.SQLException;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.PostRequestFailedException;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.connectors.ObjectBuilder;
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
	public HttpResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		// TODO: remove this method this one is not used
		///	JsonElement je = gson.toJsonTree(objectToPost);
		//	JsonObject jo = new JsonObject();
		//	jo.add(rootValue, je);
		//Log.writeInfo("Json object" + jo.toString());
		//ResponseData data = HttpRequest.sendPostRequest(this.serverUrl + resourceUrl,jo.toString());
		//return data;
		return null;
	}
	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl) throws PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException {
		String json = objectBuilder.createStringFromObject(objectToPost);
		//	String json = gson.toJson(objectToPost);
		Log.writeInfo("Json object" + json.toString());
		HttpResponseData data = HttpRequestUtil.sendPostRequest(this.serverUrl + resourceUrl, json);
		return data;
	}
}
