package nationbuilder.lib.http;

import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.http.data.BaseServiceConnector;

/**
 * Created by patrick on 12/15/14.
 */
public class SqlServiceConnector extends BaseServiceConnector
{
	public SqlServiceConnector(String serverUrl,RubyContextType contextType,ObjectBuilder objectBuilder)
	{
		super(serverUrl);
		switch (contextType)
		{
			case BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT:
				this.setFetchService(new JsonFetchServiceConnector(serverUrl,objectBuilder));
			break;
			default:
				// TODO: implement
		}
		this.setCreateService(new SqlCreateServiceConnector());
	}

}
