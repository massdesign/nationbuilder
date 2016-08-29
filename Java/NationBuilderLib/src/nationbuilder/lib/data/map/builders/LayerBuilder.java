package nationbuilder.lib.data.map.builders;

import java.util.ArrayList;
import nationbuilder.lib.Ruby.Exceptions.RubyDataServiceNotInitializedException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.entities.Layer;
import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.exceptions.MapConvertException;
import nationbuilder.lib.data.map.xml.XmlLayer;
import nationbuilder.lib.data.map.xml.XmlTile;

/**
 * @author patrick.ekkel
 */
public class LayerBuilder
{
	private RubyContext rubyContext;
	private int zindex;
	private MapMap map;
	private ArrayList<Image> images;
	// NOTE: tijdelijke oplossing
	private ArrayList<Tile> tiles;

	// NOTE: tijdelijke oplossing
	private ArrayList<Layer> layers;

	public LayerBuilder(RubyContext rubyContext,MapMap map,ArrayList<Image> images) {

		this.rubyContext = rubyContext;
		this.map = map;
		this.images = images;
		this.tiles = new ArrayList<>();
		this.layers = new ArrayList<>();
	}

	// Tijdelijk
	public ArrayList<Tile> getTiles() {
		return this.tiles;
	}

	// Tijdelijk
	public ArrayList<Layer> getLayers() { return this.layers; }

	public Layer createLayer(XmlLayer xmlLayer) throws RubyDataServiceNotInitializedException, MapConvertException
	{

		Layer result = this.rubyContext.createRubyModel(Layer.class);

		ArrayList<XmlTile> tiles = xmlLayer.getTiles();

		result.setZindex(this.zindex);
		//result.setMap(this.map);
		result.setName(xmlLayer.getName());
		result.setTileHeight(xmlLayer.getHeight());
		result.setTileWidth(xmlLayer.getWidth());
		int tilepositionx = 0;
		int tilepositiony = 0;
		for(XmlTile tile : tiles) {

			if (tile.getGID() != 0)
			{

				TileBuilder tileBuilder = new TileBuilder(result,this.rubyContext,this.images);
				Tile newTile = tileBuilder.createTile(tilepositionx,tilepositiony,tile);
				this.tiles.add(newTile);
				result.addTile(newTile);

			} if (tilepositionx == xmlLayer.getWidth() - 1)
			{
				tilepositionx = 0;
				tilepositiony++;
			}
			else
			{
				tilepositionx++;
			}

		}
		zindex++;

		this.layers.add(result);
		return result;
	}

}
