package nationbuilder.lib.Ruby;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.ObjectFetchFailedException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.http.data.HttpData;

/**
 * Created by patrick on 10/21/14.
 */
public class RubyObjectFactoryImpl<T extends RubyModel> implements RubyObjectFactory
{

	RubyContext context;
	Class<T> clazz;
	Class<?> clazzArray;
	Gson gson;
	public RubyObjectFactoryImpl(Class<T> clazz,Class<?> clazzArray)
	{
		this.clazz = clazz;
		this.clazzArray = clazzArray;
		this.gson = new Gson();
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
	public T get(int id)
	{
		T result = null;

		HttpData data = this.context.getRubyService().getObject("/" + getRequestUrl(clazz) + "/" + id);
		if(data != null)
		{
		 result  = 	(T)gson.fromJson(data.getBody(),this.clazz);
		}
		return result;
	}

	@Override
	public List<T> getAll()
	{
		List<T> result = new ArrayList<T>();
	    String requestUrl =  getRequestUrl(clazz);
		HttpData data =  this.context.getRubyService().getObject("/" + requestUrl);
		if(data != null)
		{
			T[] resultArray = (T[]) gson.fromJson(data.getBody(), clazzArray);
			for (int i = 0; i < resultArray.length; i++)
			{
				resultArray[i].setRubyContext(this.context);
				result.add(resultArray[i]);
			}
		}
		return result;
	}

	@Override // TODO: add ObjectFetchFailedException to all methods
	public List get(String action, String... args) throws ObjectFetchFailedException
	{
		List<T> result = new ArrayList<T>();
		String query = "";
		// construct query
		for(String arg : args)
		{
			query += arg + "/";
		}
		String requestUrl = getRequestUrl(clazz);
		String actionUrl = "/" + requestUrl + "/" + action + "/" + query.substring(0,query.length()-1);
	    HttpData resultSet =	this.context.getRubyService().getObject(actionUrl);

		if(resultSet.getResponseCode() == 200 || resultSet.getResponseCode() == 201)
		{
			T[] resultArray = null;
			T resultObject = null;
			try
			{
			// TODO: write code that adds the results of the array to the resultList
			 resultArray = (T[]) gson.fromJson(resultSet.getBody(), clazzArray);
			}
			catch (JsonSyntaxException ex)
			{
				Log.write(ex, LogType.WARNING);
			}
			// it is not an array so try to create an object from it
			if(resultArray == null)
			{
				try
				{
					resultObject = (T) gson.fromJson(resultSet.getBody(), this.clazz);
				}
				catch (JsonSyntaxException ex)
				{
					Log.write(ex, LogType.WARNING);
				}

				// if objects creation fails than we might not get the object we want back from the stream.
				if (resultObject == null)
				{
					String errorMessage =
					 "Failed to retrieve the object from the returned JSON stream, request contents: " + resultSet
					  .getBody();
					throw new ObjectFetchFailedException(errorMessage);
				}
				else
				{
					result.add(resultObject);
				}
			}
		}
		return  result;
	}
}
