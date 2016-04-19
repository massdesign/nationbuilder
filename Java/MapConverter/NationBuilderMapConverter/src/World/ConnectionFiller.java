package World;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.ConnectionType;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.entities.Node;
import nationbuilder.lib.data.map.entities.PowerConnection;
import nationbuilder.lib.data.map.entities.PowerGridNode;
import nationbuilder.lib.data.map.entities.PowerRelayStation;
import nationbuilder.lib.data.map.entities.PowerRelayStationType;

/**
 * @author patrick.ekkel
 */
public class ConnectionFiller extends BaseFiller
{
	private MapDataset mapDataset;
	public ConnectionFiller(RubyContext context)
	{
		super(context);
	}


	private ConnectionType createConnectionType(String name) {

		ConnectionType connectionType = this.getContext().createRubyModel(ConnectionType.class);

		connectionType.setName(name);
		//connectionType.
		return connectionType;
	}
	private Node createNodeType(String name,boolean destroyable,PowerRelayStation powerRelayStation) {

		PowerGridNode nodeType = this.getContext().createRubyModel(PowerGridNode.class);
		nodeType.setName(name);
		nodeType.setDestroyable(destroyable);
		nodeType.setRelayStation(powerRelayStation);

		return nodeType;
	}
	private PowerRelayStation createPowerRelayStation(String  name,PowerRelayStationType powerRelayStationType) {

		PowerRelayStation powerRelayStation  = this.getContext().createRubyModel(PowerRelayStation.class);
		powerRelayStation.setName(name);
		powerRelayStation.setPowerRelayStationType(powerRelayStationType);

		return powerRelayStation;
	}
	private PowerRelayStationType createPowerRelayStationType(int capacity,String name) {

		PowerRelayStationType powerRelayStationType = this.getContext().createRubyModel(PowerRelayStationType.class);

		powerRelayStationType.setName(name);
		powerRelayStationType.setCapacity(capacity);

		return powerRelayStationType;
	}
	private PowerGridNode createPowerGridNode(PowerRelayStation powerRelayStation,String name) {

		PowerGridNode powerGridNode = this.getContext().createRubyModel(PowerGridNode.class);

		powerGridNode.setRelayStation(powerRelayStation);
		powerGridNode.setName(name);

		return powerGridNode;
	}

	private PowerConnection createPowerConnection(PowerGridNode A,PowerGridNode B) {

		PowerConnection powerConnection = this.getContext().createRubyModel(PowerConnection.class);
		powerConnection.setA(A);
		powerConnection.setB(B);
		return powerConnection;
	}



	@Override
	public void Fill() throws ObjectConversionFailedException
	{
		PowerRelayStationType powerRelayStationType = createPowerRelayStationType(300,"Small Power Plant");
		PowerRelayStation prs01  = createPowerRelayStation("PRS01",powerRelayStationType);
		PowerRelayStation prs02 = createPowerRelayStation("PRS02", powerRelayStationType);
		// pick a random tile (improve later by picking only land tiles and making that random)
		prs01.setLocatedOn(this.mapDataset.getMapTiles().get(5));
		prs02.setLocatedOn(this.mapDataset.getMapTiles().get(10));



		PowerGridNode powerGridNode01 = createPowerGridNode(prs01,"pgn01");
		PowerGridNode powerGridNode02 = createPowerGridNode(prs01,"pgn02");


		PowerConnection powerConnection = createPowerConnection(powerGridNode01,powerGridNode02);

		Node powerNodeType = createNodeType("power node 1 ", true, prs01);


		this.getRubyModels().add(powerConnection);
		this.getRubyModels().add(prs01);
		this.getRubyModels().add(prs02);
		this.getRubyModels().add(powerNodeType);
		this.getRubyModels().add(powerGridNode01);
		this.getRubyModels().add(powerGridNode02);
		this.getRubyModels().add(powerRelayStationType);


	}

	public void setMapDataset(MapDataset mapDataset)
	{
		this.mapDataset = mapDataset;
	}
}
