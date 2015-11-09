package nationbuilder.lib.data.map.entities;

import java.util.List;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * Created by patrick on 9/19/14.
 */
public class Connection extends BaseRubyModel
{

	public boolean isOneDirectional()
	{
		return oneDirectional;
	}

	private boolean oneDirectional;

	/**
	 * A is the source if we are speaking from a one way perspective
	 */
	private List<StaticEntity> A;
	/**
	 * 	 * B is the endpoint if we are speaking from a one way perspective
	 */
	private List<StaticEntity> B;
}
