package nationbuilder.lib.Ruby;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.services.ClassMapService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;

/**
 * @author patrick.ekkel
 */
public class ModelPayloadBuilder
{
	private RubyModel rubyModel;
	private RubyContext context;
	public ModelPayloadBuilder(RubyModel rubyModel,RubyContext context) {
		this.rubyModel = rubyModel;
		this.context = context;
	}

	public ModelPayload build() {

		ClassMapService classMapService;
		ClassMap classMap = null;
		InhiritanceStrategy inhiritanceStrategy = InhiritanceStrategy.OneTablePerInstance;
		try
		{
			classMapService = RubyDataServiceAccessor.getInstance().getService(ClassMapService.class);
			classMap = classMapService.createClassMap(rubyModel);
			if (RubyAssociationResolver.StrategyIsTablePerClass(rubyModel) && !context.getRubyService().ignoreClassMapInsertStrategy())
			{
			 inhiritanceStrategy = InhiritanceStrategy.TablePerClass;
			}
			else {
				// Heel belangrijk anders krijgen we nullpointers omdat er niet over de classMap heen wordt geloopt
				classMap.loadDefault();
			}
		}
		catch (RubyDataServiceNotInitializedException e)
		{
			Log.write(e, LogType.ERROR);
		}
		return new ModelPayload(classMap,rubyModel,inhiritanceStrategy);
	}
}
