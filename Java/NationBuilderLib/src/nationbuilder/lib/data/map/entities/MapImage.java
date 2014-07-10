package nationbuilder.lib.data.map.entities;

import java.io.File;

import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.ID;


public class MapImage extends BaseRubyModel {
		
	@Override
	public String toString() {
		return "MapImage [url=" + url + ", name=" + name + ", width=" + width
				+ ", height=" + height + ", firstGid=" + firstGid
				+ ", lastGid=" + lastGid + ", tileWidth=" + tileWidth
				+ ", tileHeight=" + tileHeight + ", imageFile=" + imageFile
				+ ", id=" + id + "]";
	}
	private String url;
	private String name;
	private int width;
	private int height;
	private int firstGid;
	private int lastGid;
	private int tileWidth;
	private int tileHeight;
	private MapImageFile imageFile;

    public MapMap getMap() {
        return map;
    }

    public void setMap(MapMap map) {
        this.map = map;
    }

    private MapMap map;
	
	public MapImage()
	{
	}

    public void fetchIDs()
    {
        this.mid = Integer.parseInt(this.getMap().getId().getId());
    }
    private int mid;
	private ID id;
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public MapImageFile getImageFile() {
		return imageFile;
	}
	public void setImageFile(MapImageFile imageFile) {
		this.imageFile = imageFile;
	}
	public int getTileWidth() {
		return tileWidth;
	}
	public void setTileWidth(int tileWidth) {
		this.tileWidth = tileWidth;
	}
	public int getTileHeight() {
		return tileHeight;
	}
	public void setTileHeight(int tileHeight) {
		this.tileHeight = tileHeight;
	}
	public int getFirstGid() {
		return firstGid;
	}
	public void setFirstGid(int firstGid) {
		this.firstGid = firstGid;
	}
	public int getLastGid() {
		return lastGid;
	}
	public void setLastGid(int lastGid) {
		this.lastGid = lastGid;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
