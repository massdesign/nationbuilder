package World;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Node;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;

/**
 * @author patrick.ekkel
 */
public class ConnectionFiller extends BaseFiller
{
	public ConnectionFiller(RubyContext context)
	{
		super(context);
	}


	/*private ConnectionType_old createConnectionType(String name) {

		ConnectionType_old connectionType = this.getContext().createRubyModel(ConnectionType_old.class);

		connectionType.setName(name);
		//connectionType.
		return connectionType;
	}*/
	private Node createNodeType(String name,boolean destroyable,PowerRelayStation powerRelayStation) {

		PowerGridNode nodeType = this.getContext().createRubyModel(PowerGridNode.class);
		nodeType.setName(name);
		nodeType.setDestroyable(destroyable);
		//nodeType.setRelayStation(powerRelayStation);

		return nodeType;
	}
	private PowerRelayStation createPowerRelayStation(String  name,int capacityInMw) {

		PowerRelayStation powerRelayStation  = this.getContext().createRubyModel(PowerRelayStation.class);
		powerRelayStation.setName(name);
		return powerRelayStation;
	}



	@Override
	public void Fill() throws ObjectConversionFailedException
	{
		/*this.getRubyModels().add(createConnectionType("Power Grid"));
		this.getRubyModels().add(createConnectionType("Highway"));
		this.getRubyModels().add(createConnectionType("Tradehub"));*/

		PowerRelayStation prs01  = createPowerRelayStation("PRS01",300);
		this.getRubyModels().add(prs01);
		Node powerNodeType = createNodeType("power node 1 ",true,prs01);
		this.getRubyModels().add(powerNodeType);
	}
}
