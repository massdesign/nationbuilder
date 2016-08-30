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


        RubyContext testContext = new DefaultRubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT,Main.class);

        // TODO: kolomnamen zijn nog hoofdletter gevoelig.. dit kunnen we makkelijk fixen met een toLower
      //  RubyContext context = new DefaultRubyContextFactory()
       //  .createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT, Main.class);
       // WorldLoader worldLoader = new WorldLoader(context);
        //worldLoader.Run();

        PowerRelayStation powerRelayStation1 =  testContext.createRubyModel(PowerRelayStation.class);
        Tile tile1 = testContext.createRubyModel(Tile.class);
        tile1.addBuilding(powerRelayStation1);
        tile1.Save();
        testContext.commit();
    }

}



