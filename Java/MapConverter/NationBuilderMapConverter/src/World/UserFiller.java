package World;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Secure.PasswordHash;
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

	public void Fill()
	{
		this.getRubyModels().add(createUser("test", "Henk de tester", "test"));
	}

}
