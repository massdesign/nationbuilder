package nationbuilder.lib.data.map.entities;

/**
 * Created by patrick on 9/18/14.
 */

import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Used as a base class for all actors in the game..
 */
public class GameEntity extends BaseRubyModel
{
	private String name;
	private User ruledBy;
}
