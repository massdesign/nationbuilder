package nationbuilder.lib.Ruby.orm;

import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public class RubyObjectKey
{


	public RubyObjectKey(String table,RubyModel object) {
		this.table = table;
		this.object = object;
	}

	private RubyModel object;
	private String table;

	public String getTable()
	{
		return table;
	}

	public void setTable(String table)
	{
		this.table = table;
	}

	public RubyModel getObject()
	{
		return object;
	}

	public void setObject(BaseRubyModel object)
	{
		this.object = object;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof RubyObjectKey) {

		 RubyObjectKey rubyObjectKey =	(RubyObjectKey)o;

			return rubyObjectKey.getObject().getId() == this.getObject().getId() && rubyObjectKey.getTable().equals(this.getTable());
		}
		else {
			return false;
		}
	}

	@Override
	public int hashCode()
	{
		int hashcode = 1;
		hashcode = 31 * hashcode + this.getObject().hashCode() + this.getTable().hashCode();
		return hashcode;
	}
}
