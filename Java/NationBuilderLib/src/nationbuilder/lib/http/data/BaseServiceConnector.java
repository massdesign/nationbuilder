package nationbuilder.lib.http.data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.PostRequestFailedException;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyFetchService;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.HttpRequestUtil;

/**
 * Created by patrick on 12/12/14.
 */
public class BaseServiceConnector implements RubyService
{
	private RubyFetchService fetchService;
	private RubyCreateService createService;
	private String serverUrl;


	public BaseServiceConnector(String serverUrl)
	{
		this.serverUrl = serverUrl;
	}

	public String getServerUrl()
	{
		return serverUrl;
	}

	public void setServerUrl(String serverUrl)
	{
		this.serverUrl = serverUrl;
	}
	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}

	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException {
		return null;
	}

	// TODO: resources worden nog even apart behandeld, het is nog niet nodig om dit mechanmisme generiek te maken
	public int postFile(String fileLocation, String resourceUrl) throws IOException
	{
		File file = new File(fileLocation);

		int status_code = HttpRequestUtil.sendPostUploadRequest(this.getServerUrl() + resourceUrl, file);

		return status_code;
	}

	public int postFile(BaseRubyResourceModel model, String resourceUrl) throws IOException
	{
		int status_code = HttpRequestUtil.sendPostUploadRequest(this.getServerUrl() + resourceUrl, model.getResource());
		return status_code;
	}

	@Override
	public HttpResponseData getObject(String resourceUrl)
	{
		return null;
	}

	public RubyFetchService getFetchService()
	{
		return fetchService;
	}

	public void setFetchService(RubyFetchService fetchService)
	{
		this.fetchService = fetchService;
	}

	public RubyCreateService getCreateService()
	{
		return createService;
	}

	public void setCreateService(RubyCreateService createService)
	{
		this.createService = createService;
	}
}
