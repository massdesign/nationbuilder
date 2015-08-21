package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;


import nationbuilder.lib.Ruby.Association.annotation.Entity;
import java.util.ArrayList;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.BaseRubyModel;

import java.util.List;
import nationbuilder.lib.Ruby.ReferenceMapping;


@Entity(tableName = "tiles")
public class Tile extends BaseRubyModel {

	
	private int xposition;
	private int yposition;
    private int gidtag;
	private int xoffset;
	private int yoffset;

    private List<GameEntity> claimedBy;
    private GameEntity owner;
	@OneToOne(mapIdTo = "imd")
	private Image image;
	@OneToOne(mapIdTo = "lmd")
	private Layer layer;

	@OneToOne(mapIdTo = "tti")
	private TerrainType terrainType;

	// terraintype for reference in backend
	private String tti;

	@OneToMany(mapIdTo = "rids",mappedBy = "tile",mappedByClazz = Resource.class)
	private List<Resource> resources = new ArrayList<>();

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

	private int [] rids;
	public int getImageId()
	{
		return  Integer.parseInt(this.image.getId().getId());
	}

	public Image getImage() {
		return image;
	}
	public void setImage(Image image) {
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


    public GameEntity getOwner() {
        return owner;
    }

    public void setOwner(GameEntity owner) {
        this.owner = owner;
    }

    /*public List<GameEntity> getClaimedBy() {
        return claimedBy;
    }

    public void setClaimedBy(List<GameEntity> claimedBy) {
        this.claimedBy = claimedBy;
    }*/

	public void addResource(Resource resource) {
		this.resources.add(resource);
	}

	public List<Resource> getResources() {
		return this.resources;
	}

	public TerrainType getTerrainType()
	{
		return terrainType;
	}

	public void setTerrainType(TerrainType terrainType)
	{
		this.terrainType = terrainType;
	}

}
