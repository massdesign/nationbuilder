package nationbuilder.lib.http;

import java.io.IOException;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.PostRequestFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.data.ResponseData;

/**
 * @author patrick.ekkel
 */
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
	public ResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return this.writeService.postObject(objectToPost,resourceUrl,rootValue);
	}

	@Override
	public ResponseData postObject(Object objectToPost, String resourceUrl) throws IOException, ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException
	{
		return this.writeService.postObject(objectToPost,resourceUrl);
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
	public void commit() throws RubyException
	{
		this.writeService.commit();
	}
}
