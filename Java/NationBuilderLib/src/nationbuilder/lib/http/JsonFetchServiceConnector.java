package nationbuilder.lib.http;

import nationbuilder.lib.Ruby.Interfaces.RubyFetchService;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.http.data.HttpResponseData;

/**
 * Created by patrick on 12/12/14.
 */
public class JsonFetchServiceConnector implements RubyFetchService
{
	private String serverUrl;
	private ObjectBuilder objectBuilder;
	public JsonFetchServiceConnector(String serverUrl, ObjectBuilder objectBuilder)
	{
		this.serverUrl = serverUrl;
		this.objectBuilder = objectBuilder;
	}

	@Override
	public HttpResponseData getObject(String resourceUrl)
	{
		return null;
	}
}
