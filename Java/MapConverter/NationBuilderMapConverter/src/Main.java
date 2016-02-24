import java.io.IOException;

<<<<<<< 879453dba87bc81b79f95d346116bc3e60b86115
=======

>>>>>>> NB-13: tableperclass werkt nu zoals het zou moeten
import java.io.IOException;
import World.WorldLoader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;


public class Main {

    public static void main(String[] args) throws IOException, RubyException {

        long startTime  = System.currentTimeMillis();

        RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();


        long endtime = System.currentTimeMillis();

        Date date = new Date((endtime - startTime));
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        System.out.println("Total running time " + dateFormatted);

    }

}



