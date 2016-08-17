import World.WorldLoader;
import java.lang.reflect.InvocationTargetException;
import nationbuilder.lib.Ruby.DefaultRubyContextFactory;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.WareHouse;


public class Main {

    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, RubyException, NoSuchFieldException
    {
        final RubyContext context = new DefaultRubyContextFactory()
         .createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT, Main.class);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.TestRun();
    }

}



