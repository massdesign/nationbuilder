
import java.io.IOException;
import java.sql.SQLException;

import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyConfiguration;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.Currency;
import nationbuilder.lib.data.map.entities.State;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.entities.User;
import nationbuilder.lib.http.data.SqlQueryManager;
import nationbuilder.lib.sql.ObjectMap;
import nationbuilder.lib.sql.SqlObjectToRowConverter;


public class Main {
	public static void main(String[] args) throws IOException, RubyException, SQLException {
        RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);

		State state1 = context.createRubyModel(State.class);
		state1.setMotto("bier en tieten");
		User user1 = context.createRubyModel(User.class);
		user1.setLoginname("gekke henkie");
		Currency currency = context.createRubyModel(Currency.class);
		currency.setName("The Asshole currency");
		state1.setCurrency(currency);

		user1.setGameEntity(state1);

		currency.Save("/currencies/");
		state1.Save("/states/");
		user1.Save("/users/");

		context.commit();


        //WorldLoader worldLoader = new WorldLoader(context);
        //worldLoader.Run();
   	  }
	}
	

