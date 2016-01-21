package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Association.annotation.Transient;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_grid_nodes")
public class PowerGridNode extends Node
{
    @Transient
	private String rsid;

	// TODO: one to one hier uitbreiden
	@OneToOne(mapIdTo = "rsid")
	private PowerRelayStation relayStation;

	public PowerRelayStation getRelayStation()
	{
		return relayStation;
	}

	public void setRelayStation(PowerRelayStation relayStation)
	{
		this.relayStation = relayStation;
	}
}
