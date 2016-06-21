package mocks;

import java.io.IOException;
import nationbuilder.lib.Ruby.ClassMap;
import nationbuilder.lib.Ruby.Exceptions.ColumnNotFoundException;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.PostRequestFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.connectors.BaseServiceConnector;
import nationbuilder.lib.data.map.ResponseData;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;

/**
 * @author patrick.ekkel
 */
public class MockServiceConnector extends BaseServiceConnector
{
	public MockServiceConnector(String serverUrl, boolean transacted)
	{
		super(serverUrl, transacted);
	}

	@Override
	public ResponseData postObject(RubyModel objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}

	@Override
	public ResponseData postObject(ClassMap clazzmap, RubyModel objectToPost, String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException
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

	}
}
