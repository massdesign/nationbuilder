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

	public void addEntry(String key,Object value)
	{
		this.kvMap.put(key,value);
	}

}
