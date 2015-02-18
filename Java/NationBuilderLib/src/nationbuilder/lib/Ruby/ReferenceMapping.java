package nationbuilder.lib.Ruby;

public class ReferenceMapping {

	ID id;
	Class type;
	
	
	public ID getID()
	{
		return this.id;
	}
	public Class getClassType()
	{
		return this.type;
	}
	public void setID(ID id)
	{
		this.id = id;
	}
	public void setClassType(Class type)
	{
		this.type = type;
	}
	
	public ReferenceMapping(ID id,Class type)
	{
		this.id = id;
		this.type = type;
	}
	
	
}
