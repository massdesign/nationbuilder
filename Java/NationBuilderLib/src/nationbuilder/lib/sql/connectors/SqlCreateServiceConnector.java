package nationbuilder.lib.sql.connectors;

import java.io.IOException;
import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.HttpResponseData;

/**
 * Created by patrick on 12/15/14.
 */
public class SqlCreateServiceConnector implements RubyCreateService
{
	@Override
	public HttpResponseData postObject(RubyModel objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		// TODO: this method should be deleted in the future because it seems pretty pointless atm
		return null;
	}

	@Override
	public HttpResponseData postObject(ClassMap clazz,RubyModel objectToPost, String resourceUrl)
	{

		return null;
	}

    @Override
    public void commit() {

    }
}
