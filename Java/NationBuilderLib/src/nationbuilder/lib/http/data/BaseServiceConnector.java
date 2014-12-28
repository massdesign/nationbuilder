package nationbuilder.lib.http.data;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import nationbuilder.lib.Ruby.Exceptions.*;
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
    private boolean transacted;


	public BaseServiceConnector(String serverUrl,boolean transacted)
	{
		this.serverUrl = serverUrl;
        this.transacted = transacted;
	}
    public boolean isTransacted() {
        return transacted;
    }

    public void setTransacted(boolean transacted) {
        this.transacted = transacted;
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
	public ResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}

	@Override
	public ResponseData postObject(Object objectToPost, String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException {
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

    @Override
    public void commit() throws RubyException {

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
