package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.*;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * Created by patrick on 9/18/14.
 */
// TODO: State komt niet voor in de tabel GameEnitity, waarschijnlijk  was dit nog niet zo belangerijk, maar het is wel consistent als het gedaan wordt
@Entity(tableName = "states")
public class State extends GameEntity
{
	@OneToOne(mapIdTo = MappedBy.SELF)
	private ReferenceMapping user;
	@IgnoreInRails
	private String cur;
	@IgnoreInRails
	private String reg;
	@ManyToOne(mapIdTo = "cur")
	@IgnoreInRails
	private Currency currency;

	@ManyToOne(mapIdTo = "reg")
	@IgnoreInRails
	private Regime regime;
	@Column
	private String motto;

	public String getMotto()
	{
		return motto;
	}

	public void setMotto(String motto)
	{
		this.motto = motto;
	}

	public Regime getRegime()
	{
		return regime;
	}

	public void setRegime(Regime regime)
	{
		this.regime = regime;
	}

	public Currency getCurrency()
	{
		return currency;
	}

	public void setCurrency(Currency currency)
	{
		this.currency = currency;
	}
}
