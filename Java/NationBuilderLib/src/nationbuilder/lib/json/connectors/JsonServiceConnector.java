package nationbuilder.lib.json.connectors;

import java.io.IOException;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.connectors.BaseServiceConnector;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.data.map.ResponseData;
import nationbuilder.lib.file.StandardFileBlobService;

public class JsonServiceConnector extends BaseServiceConnector  {


	public JsonServiceConnector(String databaseServerUrl,ObjectBuilder objectBuilder)
	{
		super(databaseServerUrl,false);
		this.setFetchService(new JsonFetchServiceConnector(databaseServerUrl,objectBuilder));
		this.setCreateService(new JsonCreateServiceConnector(databaseServerUrl,objectBuilder));
		this.setBlobService(new StandardFileBlobService(databaseServerUrl));
	}
	public ResponseData postObject(RubyModel objectToPost,String resourceUrl,String rootValue) throws IOException
	{
        return this.getCreateService().postObject(objectToPost,resourceUrl,rootValue);
	}
	public ResponseData postObject(Class clazz,RubyModel objectToPost,String resourceUrl) throws ObjectPersistanceFailedException, PostRequestFailedException, ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException {
		return this.getCreateService().postObject(clazz,objectToPost,resourceUrl);
	}

	@Override
	public int postFile(String fileLocation, String resourceUrl) throws IOException
	{
		return  this.getBlobService().postFile(fileLocation,resourceUrl);
		//File file = new File(fileLocation);
		//int status_code = HttpRequestUtil.sendPostUploadRequest(this.getServerUrl() + resourceUrl, file);
		//	return status_code;
	}

	@Override
	public int postFile(BaseRubyResourceModel model, String resourceUrl) throws IOException
	{
		return this.getBlobService().postFile(model,resourceUrl);
		//int status_code = HttpRequestUtil.sendPostUploadRequest(this.getServerUrl() + resourceUrl, model.getResource());
		//	return status_code;
	}

	public HttpResponseData getObject(String resourceUrl)
	{
		return this.getFetchService().getObject(resourceUrl);
	}

	@Override
	public void commit() throws RubyException
	{

	}
}
