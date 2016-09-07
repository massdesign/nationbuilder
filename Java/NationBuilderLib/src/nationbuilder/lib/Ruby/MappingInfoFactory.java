package nationbuilder.lib.Ruby;

import java.lang.reflect.Field;
import nationbuilder.lib.Ruby.Association.MappingInfo;
import nationbuilder.lib.Ruby.Association.MappingInfoType;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public class MappingInfoFactory
{
	String mappedBy;
	Class mappedByClazz;
	RubyModel instance;
	Field field;
	String foreignKey;
	public MappingInfoFactory(String mappedBy, Class mappedByClazz, RubyModel instance, Field field, String foreignKey) {

		this.mappedBy = mappedBy;
		this.mappedByClazz = mappedByClazz;
		this.instance = instance;
		this.field = field;
		this.foreignKey = foreignKey;
	}



	public MappingInfo create() throws RubyException
	{
		MappingInfo result = null;
		try
		{

			if (this.field.getAnnotation(OneToOne.class) != null)
			{
				OneToOne o = this.field.getAnnotation(OneToOne.class);
				result = new MappingInfo(o.mappedBy(), o.mappedByClazz(), this.instance, this.field, o.foreignKey());
				result.setMappingInfoType(MappingInfoType.OneToOne);
			}
			else if (this.field.getAnnotation(OneToMany.class) != null)
			{
				OneToMany o = this.field.getAnnotation(OneToMany.class);
				result = new MappingInfo(o.mappedBy(), o.mappedByClazz(), this.instance, this.field, null);
				result.setMappingInfoType(MappingInfoType.OneToMany);

			}
			else if (this.field.getAnnotation(ManyToOne.class) != null)
			{
				ManyToOne o = this.field.getAnnotation(ManyToOne.class);
				result = new MappingInfo(o.mappedBy(), o.mappedByClazz(), this.instance, this.field, null);
				result.setMappingInfoType(MappingInfoType.ManyToOne);
			}
			else if (this.field.getAnnotation(nationbuilder.lib.Ruby.Association.annotation.ID.class) != null)
			{
				nationbuilder.lib.Ruby.Association.annotation.ID o = this.field.getAnnotation(nationbuilder.lib.Ruby.Association.annotation.ID.class);
				// TODO: mappedByClazz weggehaald, dit is niet consistent
				result = new MappingInfo(null, this.instance, this.field);
				result.setMappingInfoType(MappingInfoType.IDMapping);
			}

		}
		catch (NoSuchFieldException e)
		{
			throw new RubyException("missing field on " + this.instance.toString());
		}

		return result;
	}

}
