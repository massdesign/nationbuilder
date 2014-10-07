package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 10/6/14.
 */
public class Currency extends BaseRubyModel
{
	private String name;
	private String status;
	// an indication that the currency may be traded to the outside world
	private boolean convertable = true;
}
