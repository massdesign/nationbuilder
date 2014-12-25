
import java.io.IOException;
import java.sql.SQLException;

import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.RubyConfiguration;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.http.data.SqlQueryManager;
import nationbuilder.lib.sql.SqlObjectToRowConverter;


public class Main {
	public static void main(String[] args) throws IOException, ObjectConversionFailedException, SQLException {

     /* RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();*/


        SqlQueryManager manager = new SqlQueryManager(RubyConfiguration.mySqlUsername,RubyConfiguration.mySqlPassword,RubyConfiguration.mySqlServer,RubyConfiguration.mySqlDatabase);

       // int dbId =  manager.getNextID();

        SqlObjectToRowConverter converter = new SqlObjectToRowConverter();

        //TableMetaData tableData = manager.getTableStructure("tiles");



        Tile tile = new Tile();


        tile.setXoffset(3);
        tile.setYoffset(3);
        tile.setXposition(4);
        tile.setYposition(90);
        tile.setGidtag(10);

    //    ObjectMap convertedObject =  converter.createObjectMap(tile);

        RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.TestRun();
   	  }
	}
	

