package nationbuilder.lib.http;

import java.io.IOException;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.http.data.BaseServiceConnector;
import nationbuilder.lib.http.data.HttpResponseData;

public class JsonServiceConnector extends BaseServiceConnector  {


	public JsonServiceConnector(String serverUrl,ObjectBuilder objectBuilder)
	{
		super(serverUrl);
		this.setFetchService(new JsonFetchServiceConnector(serverUrl,objectBuilder));
		this.setCreateService(new JsonCreateServiceConnector(serverUrl,objectBuilder));
	}
	public HttpResponseData postObject(Object objectToPost,String resourceUrl,String rootValue) throws IOException
	{
		return this.getCreateService().postObject(objectToPost,resourceUrl,rootValue);
	}
	public HttpResponseData postObject(Object objectToPost,String resourceUrl) throws IOException
	{
		return this.getCreateService().postObject(objectToPost,resourceUrl);
	}

	public HttpResponseData getObject(String resourceUrl)
	{
		return this.getFetchService().getObject(resourceUrl);
	}
}
