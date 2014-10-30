package nationbuilder.lib.http;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import nationbuilder.lib.http.data.HttpData;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.google.common.io.ByteStreams;


public class HttpRequest {

	private final static String USER_AGENT = "Mozilla/5.0";
	public static HttpData sendPostRequest(String url,String urlParameters,String contentType) throws IOException
	{
		HttpData result = new HttpData();
		
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type",contentType);
		con.setRequestProperty("Accept",contentType);
		
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		
		   BufferedReader in = new BufferedReader(new InputStreamReader(
           con.getInputStream()));
		   String inputLine;
		   StringBuilder sb = new StringBuilder();
		   while ((inputLine = in.readLine()) != null) 
			   sb.append(inputLine);
		wr.flush();
		wr.close();
		result.setResponseCode(con.getResponseCode());
		result.setBody(sb.toString());  
		in.close();		
		return result;
	}
	public static HttpData sendPostRequest(String url,String urlParameters) throws IOException
	{
		return sendPostRequest(url,urlParameters,"application/json");
	}
	public static int sendPostUploadRequest(String urlString,File fileLocation) throws IOException
	{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(urlString);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();        

		/* example for setting a HttpMultipartMode */
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		FileBody fileBody = new FileBody(fileLocation); //image should be a String
		builder.addPart("upload", fileBody);
		httpPost.setEntity(builder.build());
		HttpResponse response = httpclient.execute(httpPost);
		
		return response.getStatusLine().getStatusCode();
	}
	public static int sendPostUploadRequest(String urlString,File filelocation,String name) 
	{
		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(urlString);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();        

		/* example for setting a HttpMultipartMode */
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
		FileBody fileBody = new FileBody(filelocation); //image should be a String
		builder.addPart(name, fileBody);
		httpPost.setEntity(builder.build());
		HttpResponse response;
		try {
			response = httpclient.execute(httpPost);
			return response.getStatusLine().getStatusCode();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
		
		
	}
	
	
	public static  boolean sendGetFileRequest(String url,String filename)
	{
		boolean result = false;
		CloseableHttpClient client= HttpClientBuilder.create().build();
	//	client.getParams().setParameter("http.connection-manager.timeout", 5000);
		ResponseHandler<String> handler = new BasicResponseHandler();
		HttpGet httpGet = new HttpGet(url);		
		HttpResponse response;
		try {
			OutputStream output = new FileOutputStream(filename);
			
			response = client.execute(httpGet);
			InputStream data = response.getEntity().getContent();
			
			try { 
			 ByteStreams.copy(data, output);
			 result = true;
			}
			finally 
			{
				client.close();	
				output.close();
			}
		} catch (ClientProtocolException e) {
		
			return result;
		} catch (IOException e) {
		
			return result;
		}
		return result;
	}
	public static HttpData sendGetRequest(String url) 
	{
		HttpData result = new HttpData();
			
		CloseableHttpClient client= HttpClientBuilder.create().build();
		ResponseHandler<String> handler = new BasicResponseHandler();
		HttpGet httpGet = new HttpGet(url);		
		HttpResponse response;
		try {
			response = client.execute(httpGet);
			result.setBody(handler.handleResponse(response));
			result.setResponseCode(response.getStatusLine().getStatusCode());
			client.close();	
		} catch (ClientProtocolException e) {
		
			result = null;
		} catch (IOException e) {
		
			result = null;
		}
		
		

			
		return result;
	}
	
}
