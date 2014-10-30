package World;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Secure.PasswordHash;
import nationbuilder.lib.data.map.entities.Currency;
import nationbuilder.lib.data.map.entities.State;
import nationbuilder.lib.data.map.entities.User;

/**
 * Created by patrick on 10/1/14.
 */
public class UserFiller extends BaseFiller
{
	public UserFiller(RubyContext context)
	{
		super(context);
	}

	public User createUser(String loginName, String screenName, String password)
	{
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		User result = this.getContext().createRubyModel(User.class);
		result.setLoginname(loginName);
		result.setScreenname(screenName);
		result.setEmailadres("henk@henk.nl");
		result.setRegisterdate(dateFormat.format(cal.getTime()));
		try
		{
			result.setPaswordhash(PasswordHash.createHash(password));
		}
		catch (Exception ex)
		{
			Log.write(ex, LogType.ERROR);
		}



		return result;

	}
	public Currency createCurrency(String name)
	{
		Currency result = this.getContext().createRubyModel(Currency.class);
		result.setName(name);

		return  result;
	}
	public State createState(String name)
	{
		State result = this.getContext().createRubyModel(State.class);

		result.setName(name);
		result.setMotto("Kill em all!");
		return  result;
	}

	@Override
	public void Fill()
	{
		User user1 = createUser("test", "Henk de tester", "test");
		User user2 = createUser("patrick","Patrick de player","Test1234");
		Currency currency1 = createCurrency("Dollar");
		State state1 = createState("Soviet Netherlands");
		State state2 = createState("New Germany");
		state1.setCurrency(currency1);
		state2.setCurrency(currency1);
		user1.setGameEntity(state1);
		user2.setGameEntity(state2);

		this.getRubyModels().add(user1);
		this.getRubyModels().add(user2);
		this.getRubyModels().add(currency1);
		this.getRubyModels().add(state1);
		this.getRubyModels().add(state2);
	}
}
