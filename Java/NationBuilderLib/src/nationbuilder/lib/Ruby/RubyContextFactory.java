package nationbuilder.lib.Ruby;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.Exceptions.ServiceAlreadyRegisteredException;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.configuration.RubyConfiguration;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.Ruby.services.RubyDataServiceLoaderImpl;
import nationbuilder.lib.connectors.JsonObjectBuilder;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.json.connectors.JsonServiceConnector;
import nationbuilder.lib.sql.connectors.SqlServiceConnector;
import nationbuilder.lib.sql.SqlQueryManager;
import nationbuilder.lib.sql.SqlQueryManagerFactory;

/**
 * @author patrick.ekkel
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

        // NOTE: nu nog ff alle services loaden voor elk type.. in de toekomst zouden we dit in de switch kunnen plaatsen
        initializeServiceLoader();
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
            case TEST:
                // TODO: misschien in de toekomst een testcontext createn.. dit is voor nu goed genoegd
                service = createDefaultRubyContext();
                break;
        }

        return service;
    }
    private void initializeServiceLoader() {

        try
        {
            RubyDataServiceAccessor.setClazz(RubyDataServiceLoaderImpl.class);
            RubyDataServiceAccessor.getInstance().registerRubyService(RelationScanService.class);
            RubyDataServiceAccessor.getInstance().registerRubyService(RelationResolveService.class);
        }
        catch (RubyDataServiceNotInitializedException e)
        {
            Log.write(e, LogType.ERROR);
        }
        catch (ServiceAlreadyRegisteredException e)
        {
            Log.write(e,LogType.ERROR);
        }
    }
    private RubyContext createJsonRubyContext()
    {
        return createDefaultRubyContext();
    }

    private RubyContext createBulkInsertSqlJsonUpdateDeleteSelectRubyContext(RubyContextType contextType)
    {
        SqlQueryManager queryManagerManager = new SqlQueryManagerFactory().createQueryManager();
        String databaseServerUrl = String.format("%s/%s", RubyConfiguration.mySqlServer, RubyConfiguration.mySqlDatabase);
        String blobServiceUrl = String.format("%s:%s", RubyConfiguration.RubyBackend, RubyConfiguration.RubyBackendPort);
        SqlServiceConnector sqlServiceConnector = new SqlServiceConnector(databaseServerUrl, blobServiceUrl, contextType,
         true, queryManagerManager);
        JsonServiceConnector jsonServiceConnector = new JsonServiceConnector(blobServiceUrl,new JsonObjectBuilder());
        RubyService service = new RubyServiceImpl(jsonServiceConnector,sqlServiceConnector);
        //RubyService service = new SqlServiceConnector(databaseServerUrl,blobServiceUrl,contextType,true,queryManagerManager);
        RubyContext result = new RubyContext(service,new SqlObjectBuilder(queryManagerManager));




        return result;
    }
}
