package nationbuilder.lib.Ruby.resolvestrategies;

import nationbuilder.lib.Ruby.Association.MappingInfoType;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public class TablePerClassStrategy extends ResolveStrategy
{
	public TablePerClassStrategy(String name)
	{
		super(name);
	}

	@Override
	public void resolve() throws IllegalAccessException, NoSuchFieldException
	{
		RubyModel instance = this.getMappingInfo().getInstance();


	}

	@Override
	public boolean isConditionMet() throws IllegalAccessException
	{

		return this.getMappingInfo().getMappingInfoType() == MappingInfoType.IDMapping;
	}
}
