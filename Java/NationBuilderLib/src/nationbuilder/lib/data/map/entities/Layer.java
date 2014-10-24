package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.ID;

public class Layer extends BaseRubyModel {

	
	private String name;
	private int tileHeight;
	private int tileWidth;
    private String mid;

    public int getZindex() {
        return zindex;
    }

    public void setZindex(int zindex) {
        this.zindex = zindex;
    }

    private int zindex;

    public MapMap getMap() {
        return map;
    }

    public void setMap(MapMap map) {
        this.map = map;
    }
	@OneToOne(mapIdTo = "mid")
    private MapMap map;
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
}
