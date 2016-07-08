

import java.io.IOException;
import World.WorldLoader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.DefaultRubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.Ruby.services.PropertyManagerService;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.data.map.mapservice.TiledPropertyManager;


public class Main {

    public static void main(String[] args) throws IOException, RubyException {

        long startTime  = System.currentTimeMillis();

        final RubyContext context = new DefaultRubyContextFactory()
        {
            @Override
            public void loadCustomServices()
            {
                // NOTE: Geen custom  services atm
            }
        }.createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT,Main.class);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.TestRun();
        long endtime = System.currentTimeMillis();

        Date date = new Date((endtime - startTime));
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        System.out.println("Total running time " + dateFormatted);
    }

}



