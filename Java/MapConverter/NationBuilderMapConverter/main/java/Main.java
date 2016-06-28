

import java.io.IOException;
import World.WorldLoader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.Layer;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.xml.Configuration;
import nationbuilder.lib.data.map.xml.RubyDIPropertyLoader;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.TiledXmlMapFactory;


public class Main {

    public static void main(String[] args) throws IOException, RubyException {

        long startTime  = System.currentTimeMillis();

        RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT,Main.class);

        /* Layer layer = context.createRubyModel(Layer.class);
         Tile tile1 = context.createRubyModel(Tile.class);
         Tile tile2 = context.createRubyModel(Tile.class);

        layer.addTile(tile1);
        layer.addTile(tile2);

        tile1.Save("tiles");
        tile2.Save("tiles");
        layer.Save("layers");


        context.commit();
        */
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();
        long endtime = System.currentTimeMillis();

        Date date = new Date((endtime - startTime));
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        String dateFormatted = formatter.format(date);
        System.out.println("Total running time " + dateFormatted);
    }

}



