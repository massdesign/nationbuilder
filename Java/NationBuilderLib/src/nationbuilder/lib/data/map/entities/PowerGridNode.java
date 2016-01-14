package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.OneToOne;

/**
 * @author patrick.ekkel
 */
public class PowerGridNode extends NodeType
{
	// TODO: one to one hier uitbreiden
	@OneToOne
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
