package nationbuilder.lib.Ruby;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
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

		// TODO: get the all name by reflection..
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
}
