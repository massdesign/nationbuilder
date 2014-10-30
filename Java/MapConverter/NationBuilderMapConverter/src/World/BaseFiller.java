package World;

import java.util.ArrayList;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RubyContext;

/**
 * Created by patrick on 10/1/14.
 */
public abstract class BaseFiller
{
	private RubyContext context;

	private ArrayList<BaseRubyModel> rubyModels;

	public BaseFiller(RubyContext context)
	{
		this.context = context;
		this.rubyModels = new ArrayList<BaseRubyModel>();
	}

	public RubyContext getContext()
	{
		return context;
	}

	public void setContext(RubyContext context)
	{
		this.context = context;
	}

	public ArrayList<BaseRubyModel> getRubyModels()
	{
		return rubyModels;
	}

	public void setRubyModels(ArrayList<BaseRubyModel> rubyModels)
	{
		this.rubyModels = rubyModels;
	}

	public abstract void Fill();

	public  void Save(Class<?> clazz,String url)
	{
		for(RubyModel model : this.getRubyModels())
		{
			if(model.getClass() == clazz)
			{
				try
				{
					model.Save(url);
				}
				catch (RubyException e)
				{
					Log.write(e, LogType.ERROR);
				}
			}

		}

	}
}
