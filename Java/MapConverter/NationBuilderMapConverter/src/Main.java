
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
        //RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.JSON);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();

       /* MapImageFile mapImageFile = context.createRubyModel(MapImageFile.class);
        mapImageFile.setResource(new File("/home/patrick/Git/nationbuilder/Tiled/TileSets/World3.png"));

        MapMap mapMap = context.createRubyModel(MapMap.class);
        mapMap.setTileHeight(32);
        mapMap.setTileWidth(32);

        Image image = context.createRubyModel(Image.class);
        image.setName("imagename");
        image.setTileHeight(32);
        image.setTileWidth(32);
        image.setUrl("http://localhost");
        image.setImageFile(mapImageFile);
        image.setMap(mapMap);*/


       /* mapMap.Save("/maps/");
        mapImageFile.Save("/uploads/");
        image.Save("/images/"); */
        long endtime = System.currentTimeMillis();

        Date date = new Date((endtime - startTime));
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        System.out.println("Total running time " + dateFormatted);

    }

}



