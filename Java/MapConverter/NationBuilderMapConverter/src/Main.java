
import java.io.IOException;

import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;


public class Main {
	public static void main(String[] args) throws IOException, RubyException
    {
        RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();
       // MilitaryStronghold base = context.createRubyModel(MilitaryStronghold.class);
       // base.setHealth(1000); 
       // base.setName("The Third Castle!");
     //   base.Save("/militarystrongholds/");
	}
}
	

