package nationbuilder.lib.http;

import java.io.File;
import java.io.IOException;

import nationbuilder.lib.http.data.HttpData;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonServiceConnector {

	private String serverUrl;
	Gson gson;
	public JsonServiceConnector(String serverUrl)
	{
		this.serverUrl = serverUrl;
		this.gson = new Gson();
		
	}
	public HttpData postObject(Object objectToPost,String resourceUrl,String rootValue) throws IOException
	{
		JsonElement je = gson.toJsonTree(objectToPost);
		JsonObject jo = new JsonObject();
		
		jo.add(rootValue, je);
		System.out.println(jo.toString());
		HttpData data = HttpRequest.sendPostRequest(this.serverUrl + resourceUrl,jo.toString());
		return data;	
	}
	public HttpData postObject(Object objectToPost,String resourceUrl) throws IOException
	{
		String json = gson.toJson(objectToPost);
		System.out.println(json);
		HttpData data = HttpRequest.sendPostRequest(this.serverUrl + resourceUrl,json);
		return data;	
	}
	public int postFile(String fileLocation,String resourceUrl) throws IOException
	{
		File file = new File(fileLocation);
		
		int status_code = HttpRequest.sendPostUploadRequest(this.serverUrl + resourceUrl, file);
		
		
		return status_code;
	}
	
	public int postFile(File file,String resourceUrl) throws IOException
	{		
		int status_code = HttpRequest.sendPostUploadRequest(this.serverUrl + resourceUrl, file);	
		return status_code;
	}
	public void connect()
	{
		// TODO: implement
	}
	
}
