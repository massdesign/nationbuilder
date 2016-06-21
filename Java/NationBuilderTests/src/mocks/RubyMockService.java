package mocks;

import java.io.IOException;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.Exceptions.ColumnNotFoundException;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.PostRequestFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.orm.RubyObjectKey;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.data.map.ResponseData;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.sql.QueryManager;
import nationbuilder.lib.sql.SqlResponseData;
import nationbuilder.lib.sql.connectors.BulkSqlCreateServiceConnector;

/**
 * @author patrick.ekkel
 */
// TODO: kijken of we deze mock service niet kunnen vervangen door de real deal
public class RubyMockService implements RubyService
{


	private QueryManagerMock managerMock;
	private BulkSqlCreateServiceConnector bulkSqlCreateServiceConnector;
	public void setQueryManagerMock(QueryManagerMock managerMock) {
		this.managerMock = managerMock;
	}


	public RubyMockService(QueryManagerMock queryManagerMock) {

		this.managerMock = queryManagerMock;
		BulkSqlCreateServiceConnector bulkSqlCreateServiceConnector = new BulkSqlCreateServiceConnector(new SqlObjectBuilder(managerMock),managerMock);
		this.bulkSqlCreateServiceConnector = bulkSqlCreateServiceConnector;


	}


	@Override
	public ResponseData postObject(RubyModel objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}

	@Override
	public ResponseData postObject(ClassMap clazzmap, RubyModel objectToPost, String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException
	{
		this.bulkSqlCreateServiceConnector.postObject(clazzmap,objectToPost,resourceUrl);

		SqlObjectBuilder sqlObjectBuilder = new SqlObjectBuilder(managerMock);
		SqlResponseData responseData = new SqlResponseData();
		String sql;
		if (RubyAssociationResolver.StrategyIsTablePerClass(objectToPost))
		{
			sql = sqlObjectBuilder.createStringFromObject(clazzmap.getCurrent(), objectToPost);
		}
		else
		{
			sql = sqlObjectBuilder.createStringFromObject(objectToPost.getClass(), objectToPost);
		}

		RubyObjectKey rubyObjectKey = new RubyObjectKey(resourceUrl, objectToPost, clazzmap.getCurrent());
		responseData.setSql(sql);

		return responseData;
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
	public void commit() throws RubyException
	{
		bulkSqlCreateServiceConnector.commit();

	}
}
