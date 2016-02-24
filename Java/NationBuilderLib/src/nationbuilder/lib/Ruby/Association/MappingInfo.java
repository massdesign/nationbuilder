package nationbuilder.lib.Ruby.Association;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.FieldAccessor_Double;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import nationbuilder.lib.Ruby.Association.annotation.ID;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public class MappingInfo
{
	public MappingInfo(String mappedBy,Class mappedByClazz,RubyModel instance,Field field,String foreignKey)
	{
		this.mappedBy = mappedBy;
		this.mappedByClazz = mappedByClazz;
		this.instance = instance;
		this.field = field;
        this.foreignKey = foreignKey;
	}
	public MappingInfo(Class mappedByClazz,RubyModel instance,Field field) {

		this.mappedByClazz = mappedByClazz;
		this.instance = instance;
		this.field = field;
	}

	private RubyModel instance;
	private String mappedBy;
	private Class mappedByClazz;
	private Field field;
    private String foreignKey;
	private MappingInfoType mappingInfoType;

	public String getMappedBy() {
		return mappedBy;
	}
	public Class getMappedByClazz()
	{
		return mappedByClazz;
	}

	public boolean isForeignRelation()
	{
		if(this.mappingInfoType == MappingInfoType.IDMapping) {

			return true;
		}
		else  {
			return !this.mappedBy.equals("") && this.mappedByClazz != null;
		}
	}

	public String getObjectKey() {
		String result = "";
		String  mappedByClassComponent = "";
		if(mappedByClazz != null) {
			mappedByClassComponent =  mappedByClazz.getSimpleName();
		}
		// Get some unique value and create a MD5 hash to give this mapping info an uniqe Value
		result += this.mappingInfoType.toString() + mappedByClassComponent + this.instance.toString();
		try
		{
			 MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			 messageDigest.reset();
			 messageDigest.update(result.getBytes());
			 byte  [] digest = messageDigest.digest();
			BigInteger resultInt = new BigInteger(1,digest);
			String hashtext = resultInt.toString();
			// Now we need to zero pad it if you actually want the full 32 chars.
			while (hashtext.length() < 32)
			{
				hashtext = "0" + hashtext;
			}
			result = hashtext;
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean equals(Object o)
	{
		if(o instanceof MappingInfo)
		{
			return ((MappingInfo)o).getObjectKey().equals(this.getObjectKey());
		}
		else {

			return super.equals(o);
		}
	}

	public RubyModel getInstance()
	{
		return this.instance;
	}
	public Field getField()
	{
		return this.field;
	}
    public String getForeignKey() {
        return foreignKey;
    }

	public MappingInfoType getMappingInfoType()
	{
		return mappingInfoType;
	}

	public void setMappingInfoType(MappingInfoType mappingInfoType)
	{
		this.mappingInfoType = mappingInfoType;
	}
}
