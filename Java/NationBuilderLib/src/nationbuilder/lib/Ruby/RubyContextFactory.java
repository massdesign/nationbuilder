package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.connectors.JsonObjectBuilder;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.http.JsonServiceConnector;
import nationbuilder.lib.http.SqlServiceConnector;
import nationbuilder.lib.http.data.SqlQueryManager;
import nationbuilder.lib.http.data.SqlQueryManagerFactory;

/**
 * Created by patrick on 7/10/14.
 */
public class RubyContextFactory {

    /**
     * Standard Ruby context will connect with the ruby backend trough the json provided by the backend
     * @return
     */
    public RubyContext createDefaultRubyContext()
    {
        // TODO: in de toekomst moet de nog eens instelbaar gemaakt worden. Voor nu is deze methode hetzelfde als CreateJsonRubyContext
        String serverUrl = String.format("%s:%s", RubyConfiguration.RubyBackend, RubyConfiguration.RubyBackendPort);
        ObjectBuilder objectBuilder = new JsonObjectBuilder();
        RubyService service = new JsonServiceConnector(serverUrl,objectBuilder);
        RubyContext result = new RubyContext(service,objectBuilder);
        return result;
    }
    public RubyContext createRubyContext(RubyContextType contextType) {

        RubyContext service = null;

        switch (contextType)
        {
            case DEFAULT:
                service = createDefaultRubyContext();
                break;
            case JSON:
                service = createJsonRubyContext();
                break;
            case BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT:
                service = createBulkInsertSqlJsonUpdateDeleteSelectRubyContext(contextType);
                break;
        }

        return service;
    }
    private RubyContext createJsonRubyContext()
    {
        String serverUrl = String.format("%s:%s", RubyConfiguration.RubyBackend, RubyConfiguration.RubyBackendPort);
        ObjectBuilder objectBuilder = new JsonObjectBuilder();
        RubyService service = new JsonServiceConnector(serverUrl, objectBuilder);
        RubyContext result = new RubyContext(service,objectBuilder);
        return result;
    }

    private RubyContext createBulkInsertSqlJsonUpdateDeleteSelectRubyContext(RubyContextType contextType)
    {
        SqlQueryManager queryManagerManager = new SqlQueryManagerFactory().createQueryManager();
        String serverUrl = String.format("%s/%s", RubyConfiguration.mySqlServer, RubyConfiguration.mySqlDatabase);
        ObjectBuilder objectBuilder = new SqlObjectBuilder(queryManagerManager);
        RubyService service = new SqlServiceConnector(serverUrl,contextType, objectBuilder,true);
        RubyContext result = new RubyContext(service,objectBuilder);
        return result;
    }
}
