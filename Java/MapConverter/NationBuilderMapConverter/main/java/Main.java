import World.WorldLoader;
import java.lang.reflect.InvocationTargetException;
import nationbuilder.lib.Ruby.DefaultRubyContextFactory;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.builders.PowerConnectionBuilder;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
import nationbuilder.lib.data.map.entities.EnergyBuildingType;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.entities.PowerRelayStationType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.entities.WareHouse;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, RubyException, NoSuchFieldException
    {
        ///RubyContext testContext = new DefaultRubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT,Main.class);
        // TODO: kolomnamen zijn nog hoofdletter gevoelig.. dit kunnen we makkelijk fixen met e en toLower
        RubyContext context = new DefaultRubyContextFactory()
         .createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT, Main.class);
        WorldLoader worldLoader = new WorldLoader(context);
       // worldLoader.TestRun();
         worldLoader.Run();
        context.commit();
    }

}



