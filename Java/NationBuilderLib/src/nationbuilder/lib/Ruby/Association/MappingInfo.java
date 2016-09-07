package nationbuilder.lib.Ruby.Association;

import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

/**
 * @author patrick.ekkel
 */
public class MappingInfo
{

	public MappingInfo(String mappedBy,Class mappedByClazz,RubyModel instance,Field field,String foreignKey) throws NoSuchFieldException
	{
		this.mappedBy = mappedBy;

		this.instance = instance;
		this.field = field;
        this.foreignKey = foreignKey;
		if(mappedByClazz != null)
		{
			this.mappedByClazz = mappedByClazz.getSimpleName();
			if(mappedBy != null && !mappedBy.isEmpty())
			{
				this.mappedByField = mappedByClazz.getDeclaredField(mappedBy);
			}
		}
	}
	public MappingInfo(Class mappedByClazz,RubyModel instance,Field field) throws NoSuchFieldException
	{
		if(mappedByClazz != null)
		{
			this.mappedByClazz = mappedByClazz.getSimpleName();
			this.mappedByField = mappedByClazz.getDeclaredField(mappedBy);
		}
		this.instance = instance;
		this.field = field;

	}

	private RubyModel instance;
	private String mappedBy;
	private String mappedByClazz;
	private Field field;
    private String foreignKey;
	private MappingInfoType mappingInfoType;
	private Field mappedByField;

	public String getMappedBy() {
		return mappedBy;
	}
	public String getMappedByClazz()
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

	public Field getMappedByField() {
		return this.mappedByField;
	}

	public String getObjectKey() {
		String result = "";
		String  mappedByClassComponent = "";
		String  field = "";
		if(mappedByClazz != null) {
			mappedByClassComponent = mappedByClazz;
		}
		if(this.getField() != null) {
			field = this.getField().getName();
		}
		// Get some unique value and create a MD5 hash to give this mapping info an uniqe Value
		// NOTE: this.instance.toString() geeft altijd een andere waarde terug en dit zorgt ervoort dat we nooit dezelfde combinatie van strings op een mappinginfo hebben
		// TODO: this.instance.toString vervangen door het type ipv toString van het object
		result += this.mappingInfoType.toString() + mappedByClassComponent + field + this.instance.toString();


		// NOTE: dit is duur om te doen.. en het voegt niet zo heel veel toe
		/*	try
			{
				MessageDigest messageDigest = MessageDigest.getInstance("MD5");
				messageDigest.reset();
				messageDigest.update(result.getBytes());
				byte[] digest = messageDigest.digest();
				BigInteger resultInt = new BigInteger(1, digest);
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
		*/

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
