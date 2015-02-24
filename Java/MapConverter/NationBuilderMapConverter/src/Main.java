
import java.io.IOException;
import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;


public class Main {
<<<<<<< HEAD
=======
	public static void main(String[] args) throws IOException, RubyException, SQLException {
        RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);

     //  WorldLoader worldLoader = new WorldLoader(context);
     //  worldLoader.Run();


		State state = context.createRubyModel(State.class);
		state.setMotto("biertje?");
		state.Save("/states/");
		context.commit();
		RubyObjectFactory<State> factory = context.createRubyObjectFacory(State.class, State[].class);

	   	State s1 =	factory.getFirst();

		System.out.println(s1.toString());


>>>>>>> NB-19: Object Factory werkt nu door het implementeren van gemixte RubyServices

    public static void main(String[] args) throws IOException, RubyException {
        RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);
        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();
    }
}


<<<<<<< HEAD
=======
	//	context.commit();
>>>>>>> NB-19: Object Factory werkt nu door het implementeren van gemixte RubyServices


