package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.Transient;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_relay_stations",strategy = InhiritanceStrategy.TablePerClass)
public class PowerRelayStation  extends Building implements PowerGridComponent
{
	@ManyToOne(mapIdTo = "prstid")
	private PowerRelayStationType powerRelayStationType;
    @Transient
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
