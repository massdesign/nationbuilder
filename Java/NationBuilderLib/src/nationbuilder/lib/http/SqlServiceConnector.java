package nationbuilder.lib.http;

import java.io.IOException;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.http.data.BaseServiceConnector;
import nationbuilder.lib.http.data.BulkSqlCreateServiceConnector;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.http.data.ResponseData;

/**
 * Created by patrick on 12/15/14.
 */
public class SqlServiceConnector extends BaseServiceConnector
{
	public SqlServiceConnector(String serverUrl,RubyContextType contextType,ObjectBuilder objectBuilder,boolean transacted)
	{
		super(serverUrl,transacted);
		switch (contextType)
		{
			case BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT:
				this.setFetchService(new JsonFetchServiceConnector(serverUrl,objectBuilder));
				this.setCreateService(new BulkSqlCreateServiceConnector(objectBuilder));
			break;
			default:
				// TODO: implement
				this.setFetchService(null);
				this.setCreateService(new SqlCreateServiceConnector());
		}

	}

	@Override
	public ResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return getCreateService().postObject(objectToPost,resourceUrl,rootValue);
	}

	@Override
	public ResponseData postObject(Object objectToPost, String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException {

		return getCreateService().postObject(objectToPost, resourceUrl);
	}
    @Override
    public void commit() throws RubyException {
        getCreateService().commit();
    }

}
