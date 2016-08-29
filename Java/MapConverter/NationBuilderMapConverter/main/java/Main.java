import World.WorldLoader;
import java.lang.reflect.InvocationTargetException;
import nationbuilder.lib.Ruby.DefaultRubyContextFactory;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.entities.PowerRelayStationType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.entities.WareHouse;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, RubyException, NoSuchFieldException
    {
        // TODO: kolomnamen zijn nog hoofdletter gevoelig.. dit kunnen we makkelijk fixen met een toLower
       /* final RubyContext context = new DefaultRubyContextFactory()
         .createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT, Main.class);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run(); */

      //  RubyContext backendRubtTestContext  = new DefaultRubyContextFactory().createRubyContext(RubyContextType.JSON,Main.class);
        RubyContext backendRubtTestContext  = new DefaultRubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT,Main.class);
        Tile testTile1 = backendRubtTestContext.createRubyModel(Tile.class);
        Tile testTile2 = backendRubtTestContext.createRubyModel(Tile.class);
        PowerRelayStation relayStation = backendRubtTestContext.createRubyModel(PowerRelayStation.class);
        relayStation.addLocation(testTile1);
        relayStation.addLocation(testTile2);
        relayStation.Save();
        backendRubtTestContext.commit();

    }

}



