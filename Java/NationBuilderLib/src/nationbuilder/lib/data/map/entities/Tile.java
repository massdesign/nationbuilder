package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.BaseRubyModel;

public class Tile extends BaseRubyModel {

	
	private int xposition;
	private int yposition;
    private int gidtag;
	private int xoffset;
	private int yoffset;
	@OneToOne(mapIdTo = "imd")
	private MapImage image;
	@OneToOne(mapIdTo = "lmd")
	private Layer layer;
	@OneToOne(mapIdTo = "rid")
    private Resource resource;
	public Layer getLayer() {
		return layer;
	}
	public void setLayer(Layer layer) {
		this.layer = layer;
	}
	// image id copied to local instance
	private String imd;
	// layer id copied to local instance
	private String lmd;
    // resource id copied to local instance
    private String rid;
	public int getImageId()
	{
		return  Integer.parseInt(this.image.getId().getId());
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
	public Tile()
	{
		
	}
	public Tile(int xposition, int yposition, int xoffset, int yoffset) {
		super();
		this.xposition = xposition;
		this.yposition = yposition;
		this.xoffset = xoffset;
		this.yoffset = yoffset;
	}
    public Resource getResources() {
        return resource;
    }

    public void setResources(Resource resources) {
        this.resource = resources;
    }

}