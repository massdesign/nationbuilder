package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_relay_stations")
public class PowerRelayStation  extends Building
{
	@ManyToOne(mapIdTo = "prstid")
	private PowerRelayStationType powerRelayStationType;

	private String prstid;
	public PowerRelayStationType getPowerRelayStationType()
	{
		return powerRelayStationType;
	}

	public void setPowerRelayStationType(PowerRelayStationType powerRelayStationType)
	{
		this.powerRelayStationType = powerRelayStationType;
	}
}
