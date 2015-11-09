package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.IgnoreInRails;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * Created by patrick on 9/18/14.
 */
@Entity(tableName = "users")
public class User extends BaseRubyModel
{

	@IgnoreInRails
	@OneToOne(mapIdTo = "ge",mappedBy="user",mappedByClazz = State.class )
	private GameEntity gameEntity;

	private String screenname;
	@IgnoreInRails
	private String ge;
	private String loginname;

	private String paswordhash;

	private String emailadres;

	private String registerdate;

	public String getLoginname()
	{
		return loginname;
	}

	public void setLoginname(String loginname)
	{
		this.loginname = loginname;
	}

	public String getScreenname()
	{
		return screenname;
	}

	public void setScreenname(String screenname)
	{
		this.screenname = screenname;
	}

	public String getPaswordhash()
	{
		return paswordhash;
	}

	public void setPaswordhash(String paswordhash)
	{
		this.paswordhash = paswordhash;
	}

	public String getEmailadres()
	{
		return emailadres;
	}

	public void setEmailadres(String emailadres)
	{
		this.emailadres = emailadres;
	}

	public String getRegisterdate()
	{
		return registerdate;
	}

	public void setRegisterdate(String registerdate)
	{
		this.registerdate = registerdate;
	}

	public GameEntity getGameEntity()
	{
		return gameEntity;
	}

	public void setGameEntity(GameEntity gameEntity)
	{
		this.gameEntity = gameEntity;
	}



}
