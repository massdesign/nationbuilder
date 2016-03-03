package nationbuilder.lib.Ruby.services;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.MappingInfo;
import nationbuilder.lib.Ruby.resolvestrategies.OneToManyStrategy;
import nationbuilder.lib.Ruby.resolvestrategies.OneToOneStrategy;
import nationbuilder.lib.Ruby.resolvestrategies.ResolveStrategy;

/**
 * @author patrick.ekkel
 */
public class RelationResolveService implements RubyDataService
{
	private List<ResolveStrategy> strategies;

	public RelationResolveService() {

		this.strategies = new ArrayList<>();
		this.strategies.add(new OneToOneStrategy("OneToOne Resolving strategy"));
		this.strategies.add(new OneToManyStrategy("OneToMany Resolving strategy"));

	}

	public void resolveForeignKeys(List<MappingInfo> mappingList)
	{

		for (MappingInfo mappingInfo : mappingList)
		{
			if (mappingInfo.getField() != null)
			{

				for (ResolveStrategy strategy : this.strategies)
				{
					try
					{
						// TODO: dit moeten we eigenlijk refactoren.. dit is niet ideaal
						strategy.setMappingInfo(mappingInfo);
						strategy.setObjectToReference(mappingInfo.getInstance());
						if (strategy.isConditionMet())
						{

							strategy.resolve();
						}
					}
					catch (IllegalAccessException e)
					{
						Log.write(e, LogType.ERROR);
					}
					catch (NoSuchFieldException e)
					{
						Log.write(e, LogType.ERROR);
					}
				}
			}
		}

	}

}
