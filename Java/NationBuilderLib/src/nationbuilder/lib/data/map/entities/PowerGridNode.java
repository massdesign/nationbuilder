package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.OneToOne;

/**
 * @author patrick.ekkel
 */
public class PowerGridNode extends NodeType
{

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
