package nationbuilder.lib.connectors;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;
import java.util.Map;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.orm.ID;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.data.map.ResponseData;

/**
 * Created by patrick on 12/11/14.
 */
public class JsonObjectBuilder implements ObjectBuilder
{
	Gson gson;

	public JsonObjectBuilder()
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setExclusionStrategies(new ExclusionStrategy()
		{
			@Override
			public boolean shouldSkipField(FieldAttributes fieldAttributes)
			{
				if (fieldAttributes.getName().equals("context"))
				{
					return true;
				}
				else if(fieldAttributes.getName().equals("id"))
				{
					return true;
				}
				else
				{
					return false;
				}
			}

			@Override
			public boolean shouldSkipClass(Class<?> aClass)
			{
				return false;
			}
		});
		this.gson = gsonBuilder.create();

	}

	@Override
	public Object createObjectFromString(ResponseData data,Class<?> clazz) throws ObjectConversionFailedException
	{
		Object jsonResult = null;
		if(data instanceof HttpResponseData)
		{

		    String json	 =	((HttpResponseData) data).getBody();
			Map<String, Object> map = new HashMap<>();
			map = (Map<String, Object>) gson.fromJson(json, map.getClass());

			String iValue = String.valueOf((int)(double)map.get("id"));

			//jsonResult = gson.fromJson(((HttpResponseData) data).getBody(), clazz);

			ID result = new ID();
			result.setId(iValue);
			return result;
		}
		else
		{
			throw new ObjectConversionFailedException("Atttempted to convert the object but the JsonObjectBuilder recieved an instance of ResponseData that is not parseable ResponseData: " + data.getClass().getSimpleName());
		}

	}

	@Override
	public String createStringFromObject(Object object)
	{
	  return gson.toJson(object);
	}
}