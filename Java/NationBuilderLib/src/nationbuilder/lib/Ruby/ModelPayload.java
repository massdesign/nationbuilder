package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public class ModelPayload
{
	private InhiritanceStrategy inhiritanceStrategy;
	private ClassMap classMap;
	private RubyModel rubyModel;

	public ModelPayload(ClassMap classMap,RubyModel rubyModel,InhiritanceStrategy inhiritanceStrategy) {
		this.inhiritanceStrategy = inhiritanceStrategy;
		this.rubyModel = rubyModel;
		this.classMap = classMap;
	}

	public InhiritanceStrategy getInhiritanceStrategy()
	{
		return inhiritanceStrategy;
	}

	public void setInhiritanceStrategy(InhiritanceStrategy inhiritanceStrategy)
	{
		this.inhiritanceStrategy = inhiritanceStrategy;
	}

	public ClassMap getClassMap()
	{
		return classMap;
	}

	public void setClassMap(ClassMap classMap)
	{
		this.classMap = classMap;
	}

	public RubyModel getRubyModel()
	{
		return rubyModel;
	}

	public void setRubyModel(RubyModel rubyModel)
	{
		this.rubyModel = rubyModel;
	}
}
