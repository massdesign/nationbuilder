package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.BaseRubyModel;

/**
 * Created by patrick on 10/6/14.
 */
@Entity(tableName = "currencies")
public class Currency extends BaseRubyModel
{

	private String name;

	private String status;

	// an indication that the currency may be traded to the outside world
	private boolean convertable = true;
	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public boolean isConvertable()
	{
		return convertable;
	}

	public void setConvertable(boolean convertable)
	{
		this.convertable = convertable;
	}

}
