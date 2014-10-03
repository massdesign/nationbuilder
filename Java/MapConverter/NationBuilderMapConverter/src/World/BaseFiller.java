package World;

import java.util.ArrayList;
import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.RubyContext;

/**
 * Created by patrick on 10/1/14.
 */
public class BaseFiller
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
}
