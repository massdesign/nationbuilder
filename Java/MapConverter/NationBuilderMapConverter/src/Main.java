
import java.io.File;
import java.io.IOException;
import World.WorldLoader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.entities.MapImageFile;
import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.entities.Resource;
import nationbuilder.lib.data.map.entities.ResourceType;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;

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



