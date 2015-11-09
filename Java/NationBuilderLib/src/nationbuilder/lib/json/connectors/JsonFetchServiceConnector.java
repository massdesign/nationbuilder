package nationbuilder.lib.json.connectors;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Interfaces.RubyFetchService;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.http.HttpRequestUtil;
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
		Log.writeInfo("Request url: " + resourceUrl);
		HttpResponseData data = HttpRequestUtil.sendGetRequest(this.serverUrl + resourceUrl + ".json");
		return data;
	}
}
