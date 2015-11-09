package nationbuilder.lib.file;

import java.io.File;
import java.io.IOException;
import nationbuilder.lib.Ruby.Interfaces.RubyBlobService;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.HttpRequestUtil;

/**
 * Created by patrick on 1/7/15.
 */
public class StandardFileBlobService implements RubyBlobService
{
	public String getServerUrl()
	{
		return serverUrl;
	}

	private String serverUrl;
	public StandardFileBlobService(String serverUrl)
	{
		this.serverUrl = serverUrl;
	}

	@Override
	public int postFile(String fileLocation, String resourceUrl) throws IOException
	{
		File file = new File(fileLocation);

		int status_code = HttpRequestUtil.sendPostUploadRequest(this.getServerUrl() + resourceUrl, file);

		return status_code;
	}

	@Override
	public int postFile(BaseRubyResourceModel model, String resourceUrl) throws IOException
	{
		int status_code = HttpRequestUtil.sendPostUploadRequest(this.getServerUrl() + resourceUrl, model.getResource());
		return status_code;
	}
}
