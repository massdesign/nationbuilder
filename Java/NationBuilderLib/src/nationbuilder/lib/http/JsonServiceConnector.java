package nationbuilder.lib.http;

import java.io.File;
import java.io.IOException;

import com.google.gson.*;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;
import nationbuilder.lib.http.data.HttpData;

public class JsonServiceConnector implements RubyService {

	private String serverUrl;
	Gson gson;
	public JsonServiceConnector(String serverUrl)
	{
		this.serverUrl = serverUrl;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setExclusionStrategies(new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes fieldAttributes) {
                if (fieldAttributes.getName().equals("context"))
                {
                    return  true;
                }
                else
                {
                    return false;
                }
            }

            @Override
            public boolean shouldSkipClass(Class<?> aClass) {
                return  false;
            }
        });
        this.gson =  gsonBuilder.create();
	}
	public HttpData postObject(Object objectToPost,String resourceUrl,String rootValue) throws IOException
	{
		JsonElement je = gson.toJsonTree(objectToPost);
		JsonObject jo = new JsonObject();
		
		jo.add(rootValue, je);
        Log.writeInfo("Json object" + jo.toString());
        HttpData data = HttpRequest.sendPostRequest(this.serverUrl + resourceUrl,jo.toString());
		return data;	
	}
	public HttpData postObject(Object objectToPost,String resourceUrl) throws IOException
	{
		String json = gson.toJson(objectToPost);
        Log.writeInfo("Json object" + json.toString());
		HttpData data = HttpRequest.sendPostRequest(this.serverUrl + resourceUrl,json);
		return data;	
	}

	public HttpData getObject(String resourceUrl)
	{
		Log.writeInfo("Request url: " + resourceUrl);
		HttpData data = HttpRequest.sendGetRequest(this.serverUrl +  resourceUrl + ".json");
		return data;
	}
	public int postFile(String fileLocation,String resourceUrl) throws IOException
	{
		File file = new File(fileLocation);
		
		int status_code = HttpRequest.sendPostUploadRequest(this.serverUrl + resourceUrl, file);
		
		
		return status_code;
	}
	
	public int postFile(BaseRubyResourceModel model,String resourceUrl) throws IOException
	{		
		int status_code = HttpRequest.sendPostUploadRequest(this.serverUrl + resourceUrl, model.getResource());
		return status_code;
	}
	
}
