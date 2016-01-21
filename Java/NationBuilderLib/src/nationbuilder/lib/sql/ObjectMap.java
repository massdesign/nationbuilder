package nationbuilder.lib.sql;

import java.util.HashMap;

/**
 * Created by patrick on 12/19/14.
 */
public class ObjectMap
{
	private HashMap<String,Object> kvMap;

	public HashMap<String, Object> getKvMap()
	{
		return kvMap;
	}

	public void setKvMap(HashMap<String, Object> kvMap)
	{
		this.kvMap = kvMap;
	}

	public ObjectMap()
	{
		this.kvMap = new HashMap<>();
	}


	public void addEntry(ObjectMapRow row)
	{
        // Convert null value to empty values
        if(row.getValue() == null)
        {
            row.setValue("0");
        }
		this.kvMap.put(row.getKey(),row.getValue());
	}
	public ObjectMapRow createObjectMapRow(String key,Object value)
	{
		return new ObjectMapRow(key,value);
	}

	public class ObjectMapRow
	{
		private String key;
		private Object value;


		public ObjectMapRow(String key,Object value)
		{
			this.key = key;
			this.value = value;
		}
		public String getKey()
		{
			return key;
		}

		public void setKey(String key)
		{
			this.key = key;
		}

		public Object getValue()
		{
			return value;
		}

		public void setValue(String value)
		{
			this.value = value;
		}

		public ObjectMapRow()
		{
		}

	}

}
