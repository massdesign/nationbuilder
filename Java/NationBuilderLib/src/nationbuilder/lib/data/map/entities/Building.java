package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ID;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Association.annotation.Transient;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * Created by patrick on 9/18/14.
 */
@Entity(tableName = "buildings",strategy = InhiritanceStrategy.TablePerClass)
public class Building extends BaseRubyModel
{
	@OneToOne(mapIdTo = "loid")
	private Tile locatedOn;

	@Transient
	private String loid;

    @Transient
	private String geo;
    // TODO: dit transient gemaakt, maar dit is een workaround. superclassing en subclassing moeten we later uitwerken
    private String name;
	@ManyToOne(mapIdTo = "geo")
	private GameEntity owner;

	@ID()
	private ReferenceMapping power_relay_station;

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public GameEntity getOwner()
	{
		return owner;
	}

	public void setOwner(GameEntity owner)
	{
		this.owner = owner;
	}

	public Tile getLocatedOn()
	{
		return locatedOn;
	}

	public void setLocatedOn(Tile locatedOn)
	{
		this.locatedOn = locatedOn;
	}
}
