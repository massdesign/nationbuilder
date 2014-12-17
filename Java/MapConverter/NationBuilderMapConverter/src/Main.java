
import java.io.IOException;
import java.sql.SQLException;

import World.PreFiller;
import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.RubyConfiguration;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.converter.TiledMapConverter;
import nationbuilder.lib.data.map.mapservice.MapServiceConnector;
import nationbuilder.lib.data.map.xml.Configuration;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.TiledXmlMapFactory;
import nationbuilder.lib.http.data.SqlQueryManager;


public class Main {
	public static void main(String[] args) throws IOException, ObjectConversionFailedException, SQLException {

     /* RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();*/


        SqlQueryManager manager = new SqlQueryManager(RubyConfiguration.mySqlUsername,RubyConfiguration.mySqlPassword,RubyConfiguration.mySqlServer,RubyConfiguration.mySqlDatabase);



        manager.getNextID();


   	  }
	}
	

