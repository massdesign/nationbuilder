package World;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.entities.Resource;
import nationbuilder.lib.data.map.entities.ResourceType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;

/**
 * @author patrick.ekkel
 */
public class PostMapFiller
{
	private RubyContext context;

	private ArrayList<BaseRubyModel> rubyModels;
	private MapDataset mapDataset;

	public PostMapFiller(RubyContext context) {

		this.rubyModels = new ArrayList<>();
		this.context = context;

	}

	public void Fill() {
		fillResourceTypes();
		this.save();
	}

	private void fillResourceTypes()
	{
		List<Tile> tiles =  this.getMapDataset().getMapTiles();


		this.rubyModels.add(createResourceType("Oil", false, RESOURCELOCATION.SUBTERRAINIAN));
		this.rubyModels.add((createResourceType("Gold", false, RESOURCELOCATION.SUBSURFACE)));
		this.rubyModels.add(createResourceType("Iron", false, RESOURCELOCATION.EMBEDDEDROCK));
		this.rubyModels.add(createResourceType("Natural Gas", false, RESOURCELOCATION.CRUST));
	}

	private void addResourceToTile(Tile tile,int index) {
		Resource resource = this.context.createRubyModel(Resource.class);
		resource.setResourceType((ResourceType) this.rubyModels.get(index));
		tile.addResource(resource);
		this.rubyModels.add(resource);
	}
	private void save() {

		String resourceTypeUrl = "resourcetypes";
		String resourceUrl = "resources";
	}

	private ResourceType createResourceType(String name, boolean regenerateing, RESOURCELOCATION location)
	{
		ResourceType result = context.createRubyModel(ResourceType.class);
		result.setName(name);
		result.setRegenerating(regenerateing);
		result.setLocation(location);
		return result;
	}

	public MapDataset getMapDataset()
	{
		return mapDataset;
	}

	public void setMapDataset(MapDataset mapDataset)
	{
		this.mapDataset = mapDataset;
	}
}
