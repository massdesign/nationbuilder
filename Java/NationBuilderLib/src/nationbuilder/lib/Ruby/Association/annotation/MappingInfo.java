package nationbuilder.lib.Ruby.Association.annotation;

import java.lang.reflect.Field;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public class MappingInfo
{
	public MappingInfo(String mappedBy,Class mappedByClazz,RubyModel instance,Field field)
	{
		this.mappedBy = mappedBy;
		this.mappedByClazz = mappedByClazz;
		this.instance = instance;
		this.field = field;
	}

	private RubyModel instance;
	private String mappedBy;
	private Class mappedByClazz;
	private Field field;

	public String getMappedBy() {
		return mappedBy;
	}
	public Class getMappedByClazz()
	{
		return mappedByClazz;
	}

	public boolean isForeignRelation()
	{
		return !this.mappedBy.equals("") && this.mappedByClazz != null;
	}
	public RubyModel getInstance()
	{
		return this.instance;
	}
	public Field getField()
	{
		return this.field;
	}

}
