package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.MappedBy;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

@Entity(tableName = "layers")
public class Layer extends BaseRubyModel {

	@Column
	private String name;
	@Column
	private int tileHeight;
	@Column
	private int tileWidth;
    private String mid;
	// placeholder voor ids
	private int [] tids;

	@ManyToOne(mapIdTo = MappedBy.SELF)
	private ReferenceMapping map;

	@Column(setMethod = "addTile")
	@OneToMany(mapIdTo = "tids",mappedBy = "layer",mappedByClazz = Tile.class)
	private List<Tile> tiles;

	public Layer() {
		this.tiles = new ArrayList<>();
	}

    public int getZindex() {
        return zindex;
    }

    public void setZindex(int zindex) {
        this.zindex = zindex;
    }

    private int zindex;

   // public MapMap getMap() {
   //     return map;
   // }

   // public void setMap(MapMap map) {
  //      this.map = map;
  //  }
	//@OneToOne(mapIdTo = "mid")
  //  private MapMap map;
	public int getTileHeight() {
		return tileHeight;
	}

	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addTile(Tile tile) {
		this.tiles.add(tile);
	}
	public List<Tile> getTiles()
	{
		return tiles;
	}
}
