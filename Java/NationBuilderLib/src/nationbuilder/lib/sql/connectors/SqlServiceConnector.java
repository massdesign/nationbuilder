package nationbuilder.lib.sql.connectors;

import java.io.IOException;

import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.connectors.JsonObjectBuilder;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.data.map.ResponseData;
import nationbuilder.lib.connectors.BaseServiceConnector;
import nationbuilder.lib.json.connectors.JsonFetchServiceConnector;
import nationbuilder.lib.sql.QueryManager;
import nationbuilder.lib.sql.SqlQueryManager;
import nationbuilder.lib.file.StandardFileBlobService;

/**
 * Created by patrick on 12/15/14.
 */
public class SqlServiceConnector extends BaseServiceConnector
{
	public SqlServiceConnector(String databaseServerUrl,String blobServerUrl,RubyContextType contextType,boolean transacted,QueryManager queryManager)
	{
		super(databaseServerUrl,transacted);
		switch (contextType)
		{
			case BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT:
				this.setFetchService(new JsonFetchServiceConnector(databaseServerUrl,new JsonObjectBuilder()));
				// TODO: 2 keer hetzelfe meegeven is niet zo optimaal
				this.setCreateService(new BulkSqlCreateServiceConnector(new SqlObjectBuilder(queryManager),queryManager));
			break;
			default:
				// TODO: implement
				this.setFetchService(null);
				this.setCreateService(new SqlCreateServiceConnector());
		}

		this.setBlobService(new StandardFileBlobService(blobServerUrl));

	}

	@Override
	public ResponseData postObject(RubyModel objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return getCreateService().postObject(objectToPost,resourceUrl,rootValue);
	}

	@Override
	public ResponseData postObject(ClassMap clazz,RubyModel objectToPost, String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException {

		return getCreateService().postObject(clazz,objectToPost, resourceUrl);
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
	public boolean ignoreClassMapInsertStrategy()
	{
		return false;
	}

	@Override
	public boolean ignoreClassMapSelectStrategy()
	{
		return false;
	}


	@Override
    public void commit() throws RubyException {
        getCreateService().commit();
    }



}
