package nationbuilder.lib.Ruby.resolvestrategies;

import java.lang.reflect.Field;
import java.util.List;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;
import nationbuilder.lib.Ruby.resolvestrategies.ResolveStrategy;

/**
 * @author patrick.ekkel
 */
public class OneToManyStrategy extends ResolveStrategy
{
	public OneToManyStrategy(String name)
	{
		super(name);
	}

	@Override
	public void resolve() throws IllegalAccessException, NoSuchFieldException
	{
		List list = (List) this.getMappingInfo().getField().get(this.getMappingInfo().getInstance());

		Field objectReferenceField = this.getMappingInfo().getMappedByClazz().getDeclaredField(this.getMappingInfo().getMappedBy());
		objectReferenceField.setAccessible(true);
		if(list != null)
		{
			for (Object foreignKeyHolder : list)
			{
				objectReferenceField.set(foreignKeyHolder,
				 new ReferenceMapping(this.getObjectToReference().getId(), this.getObjectToReference().getClass()));
			}
		}

	}

	@Override
	public boolean isConditionMet() throws IllegalAccessException
	{
		// consider making this method like TablePerClassStrategy
		boolean result = false;
		Object foreignKeyHolderField = this.getMappingInfo().getField().get(this.getMappingInfo().getInstance());
		if (foreignKeyHolderField instanceof List)
		{
			//resovleOneToManyForeignKeys(mappingInfo, objectToReference);
			result = true;
		}
		return result;
	}
}
