
import java.io.IOException;

import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.data.map.entities.MilitaryStronghold;


public class Main {
	public static void main(String[] args) throws IOException, RubyException
    {

        RubyContext context = new RubyContextFactory().createRubyContext();

        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();
       // MilitaryStronghold base = context.createRubyModel(MilitaryStronghold.class);
       // base.setHealth(1000); 
       // base.setName("The Third Castle!");
     //   base.Save("/militarystrongholds/");
	}
}
	

