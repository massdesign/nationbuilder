package nationbuilder.lib.data.map;

import nationbuilder.lib.http.data.ID;

public class MapLayer {

	
	private String name;
	private int tileHeight;
	private int tileWidth;
    private int mid;

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
    public void fetchIDs()
    {
     this.mid = Integer.parseInt(this.getMap().getId().getId());
    }
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
	
	private ID id; 
	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	} 
}
