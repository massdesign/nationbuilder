package nationbuilder.lib.connectors;

import java.io.IOException;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.data.HttpResponseData;

/**
 * Created by patrick on 12/11/14.
 */
public class SqlBulkImporter implements RubyService
{
	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}

	@Override
	public HttpResponseData postObject(Object objectToPost, String resourceUrl) throws IOException
	{
		return null;
	}

	@Override
	public int postFile(String fileLocation, String resourceUrl) throws IOException
	{
		return 0;
	}

	@Override
	public int postFile(BaseRubyResourceModel file, String resourceUrl) throws IOException
	{
		return 0;
	}

	@Override
	public HttpResponseData getObject(String resourceUrl)
	{
		return null;
	}
}
