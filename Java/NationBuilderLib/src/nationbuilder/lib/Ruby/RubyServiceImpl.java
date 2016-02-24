package nationbuilder.lib.Ruby;

import java.io.IOException;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.data.map.ResponseData;

/**
 * @author patrick.ekkel
 */

// TODO: deze klasse stelt je in staat om verschillende select en insert strategien te mixen, maar we komen in de knel met deze combi. Deze klas wegrefactoren
public class RubyServiceImpl implements RubyService
{
	RubyService writeService;
	RubyService retrieveService;

	public RubyServiceImpl(RubyService retrieveService,RubyService writeService)
	{
		this.writeService = writeService;
		this.retrieveService = retrieveService;
	}

	@Override
	public ResponseData postObject(RubyModel objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return this.writeService.postObject(objectToPost,resourceUrl,rootValue);
	}

	@Override
	public ResponseData postObject(Class clazz,RubyModel objectToPost, String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException {
		return this.writeService.postObject(clazz,objectToPost,resourceUrl);
	}

	@Override
	public int postFile(String fileLocation, String resourceUrl) throws IOException
	{
		return this.writeService.postFile(fileLocation,resourceUrl);
	}

	@Override
	public int postFile(BaseRubyResourceModel file, String resourceUrl) throws IOException
	{
		return this.writeService.postFile(file,resourceUrl);
	}

	@Override
	public ResponseData getObject(String resourceUrl)
	{
		return this.retrieveService.getObject(resourceUrl);
	}

	@Override
	public boolean ignoreClassMapInsertStrategy()
	{
		return writeService.ignoreClassMapInsertStrategy();
	}

	@Override
	public boolean ignoreClassMapSelectStrategy()
	{
		return false;
	}

	@Override
	public void commit() throws RubyException
	{
		this.writeService.commit();
	}
}
