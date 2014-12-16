package nationbuilder.lib.connectors;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
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
		if(data instanceof HttpResponseData)
		{
			return gson.fromJson(((HttpResponseData) data).getBody(), clazz);
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
