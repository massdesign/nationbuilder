package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.BaseRubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.ID;

public class MapTile extends BaseRubyModel {

	
	private int xposition;
	private int yposition;
    private int gidtag;
	private int xoffset;
	private int yoffset;
	private MapImage image;
	private MapLayer layer;
	public MapLayer getLayer() {
		return layer;
	}
	public void setLayer(MapLayer layer) {
		this.layer = layer;
	}
	private ID id;
	// image id copied to local instance
	private int imd;
	// layer id copied to local instance
	private int lmd;
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}
	public int getImageId()
	{
		return  Integer.parseInt(this.image.getId().getId());
	}
	public void fetchIDs()
	{
		this.imd = Integer.parseInt(this.image.getId().getId());
		this.lmd = Integer.parseInt(this.layer.getId().getId());	
	}
	public MapImage getImage() {
		return image;
	}
	public void setImage(MapImage image) {
		this.image = image;
	}
	public int getXposition() {
		return xposition;
	}
	public void setXposition(int xposition) {
		this.xposition = xposition;
	}
	public int getYposition() {
		return yposition;
	}
	public void setYposition(int yposition) {
		this.yposition = yposition;
	}
	public int getXoffset() {
		return xoffset;
	}
	public void setXoffset(int xoffset) {
		this.xoffset = xoffset;
	}
	public int getYoffset() {
		return yoffset;
	}
	public void setYoffset(int yoffset) {
		this.yoffset = yoffset;
	}
    public int getGidtag() {  return gidtag;  }
    public void setGidtag(int resource_id) { this.gidtag = resource_id;  }
	public MapTile()
	{
		
	}
	public MapTile(int xposition, int yposition, int xoffset, int yoffset) {
		super();
		this.xposition = xposition;
		this.yposition = yposition;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
	}


    @Override
    public boolean Save() {
        return false;
    }
}
