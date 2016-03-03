package nationbuilder.lib.Ruby.resolvestrategies;

import java.lang.reflect.Field;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Association.MappingInfo;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * @author patrick.ekkel
 */
public abstract class ResolveStrategy
{
	private MappingInfo mappingInfo;
	private RubyModel objectToReference;
	private String name;

	public void setMappingInfo(MappingInfo mappingInfo) {
		this.mappingInfo = mappingInfo;
	}

	public void setObjectToReference(RubyModel objectToReference) {
		this.objectToReference = objectToReference;
	}

	public MappingInfo getMappingInfo() {
		return this.mappingInfo;
	}
	public RubyModel getObjectToReference() {
		return this.objectToReference;
	}

	public abstract void resolve() throws IllegalAccessException, NoSuchFieldException;
	public abstract boolean isConditionMet() throws IllegalAccessException;

	public ResolveStrategy(String name) {
		this.name = name;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
