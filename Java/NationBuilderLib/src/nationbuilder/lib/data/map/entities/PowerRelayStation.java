package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Association.annotation.Transient;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_relay_stations",strategy = InhiritanceStrategy.TablePerClass)
public class PowerRelayStation  extends Building implements PowerGridComponent
{
	@Transient
	private String pgid;

	@Transient
	private String prstid;

	private int[] pcids;


	@ManyToOne(mapIdTo = "prstid")
	private PowerRelayStationType powerRelayStationType;

	@OneToOne(mapIdTo = "pgid")
	private PowerGridNode powerGridNode;

	@OneToMany(mapIdTo = "pcids", mappedBy = "power_relay_station", mappedByClazz = PowerConnection.class)
	List<PowerConnection> powerConnections = new ArrayList<>();


	public void addConnection(PowerConnection powerConnection)
	{

		this.powerConnections.add(powerConnection);
	}

	public PowerRelayStationType getPowerRelayStationType()
	{
		return powerRelayStationType;
	}

	public void setPowerRelayStationType(PowerRelayStationType powerRelayStationType)
	{
		this.powerRelayStationType = powerRelayStationType;
	}

	public PowerGridNode getPowergridNode()
	{
		return powerGridNode;
	}


	public void setPowerGridNode(PowerGridNode powerGridNode)
	{
		this.powerGridNode = powerGridNode;
	}
}
