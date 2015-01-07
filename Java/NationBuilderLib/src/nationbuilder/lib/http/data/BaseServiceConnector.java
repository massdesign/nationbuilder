package nationbuilder.lib.http.data;

import nationbuilder.lib.Ruby.Interfaces.RubyBlobService;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyFetchService;
import nationbuilder.lib.Ruby.Interfaces.RubyService;


/**
 * Created by patrick on 12/12/14.
 */
public abstract class BaseServiceConnector implements RubyService
{
	private RubyFetchService fetchService;
	private RubyCreateService createService;

	private RubyBlobService blobService;
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

	public RubyBlobService getBlobService()
	{
		return blobService;
	}

	public void setBlobService(RubyBlobService blobService)
	{
		this.blobService = blobService;
	}


}
