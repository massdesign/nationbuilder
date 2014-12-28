package nationbuilder.lib.Ruby;

import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectFetchFailedException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.connectors.JsonObjectBuilder;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.http.data.ResponseData;
import nationbuilder.lib.json.data.HttpResult;

/**
 * Created by patrick on 10/21/14.
 */
public class RubyObjectFactoryImpl<T extends RubyModel> implements RubyObjectFactory
{

	RubyContext context;
	Class<T> clazz;
	Class<?> clazzArray;
	private ObjectBuilder objectBuilder;
	//Gson gson;
	public RubyObjectFactoryImpl(Class<T> clazz,Class<?> clazzArray)
	{
		this.clazz = clazz;
		this.clazzArray = clazzArray;
		this.objectBuilder = new JsonObjectBuilder();
		//this.gson = new Gson();
	}
	private String getRequestUrl(Class<?> t)
	{
		return RubyRESTHelper.pluralize(t.getSimpleName()).toLowerCase();
	}

	@Override
	public void setRubyContext(RubyContext context)
	{
		this.context = context;
	}

	@Override
	public T get(int id) throws ObjectConversionFailedException {
		T result = null;

		ResponseData data = this.context.getRubyService().getObject("/" + getRequestUrl(clazz) + "/" + id);
		if(data != null)
		{
		result = (T)this.objectBuilder.createObjectFromString(data, this.clazz);

         if(result != null)
         {
             ID refID = new ID();
             refID.setId(Integer.toString(id));
            result.setId(refID);
         }
		}
		return result;
	}

	@Override
	public List<T> getAll() throws ObjectConversionFailedException {
		List<T> result = new ArrayList<T>();
	    String requestUrl =  getRequestUrl(clazz);
		ResponseData data =  this.context.getRubyService().getObject("/" + requestUrl);
		if(data != null)
		{
			//T[] resultArray = (T[]) gson.fromJson(data.getBody(), clazzArray);
			T[] resultArray = (T[])objectBuilder.createObjectFromString(data,clazzArray);
			for (int i = 0; i < resultArray.length; i++)
			{
				resultArray[i].setRubyContext(this.context);
				result.add(resultArray[i]);
			}
		}
		return result;
	}

	@Override // TODO: add ObjectFetchFailedException to all methods
	public List get(String action, String... args) throws ObjectFetchFailedException, ObjectConversionFailedException {
		List<T> result = new ArrayList<T>();
		String query = "";
		// construct query
		for(String arg : args)
		{
			query += arg + "/";
		}
		String requestUrl = getRequestUrl(clazz);
		String actionUrl = "/" + requestUrl + "/" + action + "/" + query.substring(0,query.length()-1);
	    ResponseData responseData =	this.context.getRubyService().getObject(actionUrl);
        // TODO: need to solve this another way
        if(responseData instanceof HttpResponseData)
        {
           HttpResponseData resultSet = (HttpResponseData)responseData;

		    if(resultSet.getResponseCode() == 200 || resultSet.getResponseCode() == 201) {
                T[] resultArray = null;
                T resultObject = null;
               try {
                  // TODO: write code that adds the results of the array to the resultList
                 resultArray = (T[]) objectBuilder.createObjectFromString(resultSet, clazzArray);
               } catch (JsonSyntaxException ex) {
                 Log.write(ex, LogType.WARNING);
              }
            // it is not an array so try to create an object from it
            if (resultArray == null) {
                try {
                    resultObject = (T) objectBuilder.createObjectFromString(resultSet, this.clazz);
                } catch (JsonSyntaxException ex) {
                    Log.write(ex, LogType.WARNING);
                }

                // if objects creation fails than we might not get the object we want back from the stream.
                if (resultObject == null) {
                    String errorMessage =
                            "Failed to retrieve the object from the returned JSON stream, request contents: " + resultSet
                                    .getBody();
                    throw new ObjectFetchFailedException(errorMessage);
                } else {
                    result.add(resultObject);
                }
            }
        }
		}
		return  result;
	}
}
