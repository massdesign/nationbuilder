package nationbuilder.lib.connectors;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.ID;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.http.data.ResponseData;

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
		Object result = null;
		if(data instanceof HttpResponseData)
		{

		    String json	 =	((HttpResponseData) data).getBody();
			Map<String, Object> map = new HashMap<>();
			map = (Map<String, Object>) gson.fromJson(json, map.getClass());

			int iValue = (int)(double)map.get("id");

			result = gson.fromJson(((HttpResponseData) data).getBody(), clazz);

			if(result != null  && result instanceof RubyModel)
			{
				ID id =  new ID();
				id.setId(String.valueOf(iValue));
				id.setType(result.getClass().getName());
				((RubyModel)result).setId(id);
			}

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
