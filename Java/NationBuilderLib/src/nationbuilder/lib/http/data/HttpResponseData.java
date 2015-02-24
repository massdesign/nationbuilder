package nationbuilder.lib.http.data;

import java.util.HashMap;

public class HttpResponseData implements ResponseData
{
private int responseCode;
private String body;



	private HashMap<String,String> objectMap;
public int getResponseCode() {
	return responseCode;
}
public void setResponseCode(int responseCode) {
	this.responseCode = responseCode;
}
public String getBody() {
	return body;
}
public void setBody(String body) {
	this.body = body;
}

public HashMap<String, String> getObjectMap() {
		return objectMap;
	} 
public void setObjectMap(HashMap<String, String> objectMap) {
		this.objectMap = objectMap;
	}

}
