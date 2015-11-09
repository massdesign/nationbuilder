package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Association.annotation.MappingInfo;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

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
