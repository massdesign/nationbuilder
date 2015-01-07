package nationbuilder.lib.http;

import java.io.File;
import java.io.IOException;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.data.BaseServiceConnector;
import nationbuilder.lib.http.data.BulkSqlCreateServiceConnector;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.http.data.ResponseData;
import nationbuilder.lib.http.data.StandardFileBlobService;

/**
 * Created by patrick on 12/15/14.
 */
public class SqlServiceConnector extends BaseServiceConnector
{
	public SqlServiceConnector(String databaseServerUrl,String blobServerUrl,RubyContextType contextType,ObjectBuilder objectBuilder,boolean transacted)
	{
		super(databaseServerUrl,transacted);
		switch (contextType)
		{
			case BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT:
				this.setFetchService(new JsonFetchServiceConnector(databaseServerUrl,objectBuilder));
				this.setCreateService(new BulkSqlCreateServiceConnector(objectBuilder));
			break;
			default:
				// TODO: implement
				this.setFetchService(null);
				this.setCreateService(new SqlCreateServiceConnector());
		}

		this.setBlobService(new StandardFileBlobService(blobServerUrl));

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
	public int postFile(String fileLocation, String resourceUrl) throws IOException
	{
		return this.getBlobService().postFile(fileLocation,resourceUrl);
	}

	@Override
	public int postFile(BaseRubyResourceModel model, String resourceUrl) throws IOException
	{
		return this.getBlobService().postFile(model,resourceUrl);
	}

	@Override
	public ResponseData getObject(String resourceUrl)
	{
		return null;
	}

	@Override
    public void commit() throws RubyException {
        getCreateService().commit();
    }



}
