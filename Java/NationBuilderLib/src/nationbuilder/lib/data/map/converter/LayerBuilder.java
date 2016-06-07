package nationbuilder.lib.data.map.converter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.entities.Layer;
import nationbuilder.lib.data.map.entities.MapMap;
import nationbuilder.lib.data.map.entities.Tile;
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


	public LayerBuilder(RubyContext rubyContext,MapMap map,ArrayList<Image> images) {

		this.rubyContext = rubyContext;
		this.map = map;
		this.images = images;
		this.tiles = new ArrayList<>();

	}

	// Tijdelijk
	public ArrayList<Tile> getTiles() {
		return this.tiles;
	}

	public Layer createLayer(XmlLayer xmlLayer) {

		Layer result = this.rubyContext.createRubyModel(Layer.class);

		ArrayList<XmlTile> tiles = xmlLayer.getTiles();

		result.setZindex(this.zindex);
		result.setMap(this.map);
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
				//newTile.setLayer(result);
				this.tiles.add(newTile);

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


		return result;
	}

}
