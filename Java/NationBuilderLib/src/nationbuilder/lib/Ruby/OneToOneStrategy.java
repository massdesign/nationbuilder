package nationbuilder.lib.Ruby;

import java.lang.reflect.Field;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * @author patrick.ekkel
 */
public class OneToOneStrategy extends ResolveStrategy
{
	public OneToOneStrategy(String name)
	{
		super(name);
	}

	@Override
	public void resolve() throws IllegalAccessException, NoSuchFieldException
	{
		RubyModel foreignKeyHolder = (RubyModel) this.getMappingInfo().getField().get(
		 this.getMappingInfo().getInstance());
		Field objectReferenceField = this.getMappingInfo().getMappedByClazz().getDeclaredField(
		 this.getMappingInfo().getMappedBy());
		objectReferenceField.setAccessible(true);
		objectReferenceField
		 .set(foreignKeyHolder, new ReferenceMapping(this.getObjectToReference().getId(), this.getObjectToReference().getClass()));

	}

	@Override
	public boolean isConditionMet() throws IllegalAccessException
	{
		boolean result = false;
		Object foreignKeyHolderField = this.getMappingInfo().getField().get(this.getMappingInfo().getInstance());
		if (foreignKeyHolderField instanceof RubyModel)
		{
			result = true;
		}

		return  result;
	}
}
