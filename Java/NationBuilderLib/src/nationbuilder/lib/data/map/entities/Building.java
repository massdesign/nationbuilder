package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ID;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.ManyToOne;
import nationbuilder.lib.Ruby.Association.annotation.MappedBy;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.Transient;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * Created by patrick on 9/18/14.
 */
@Entity(tableName = "buildings",strategy = InhiritanceStrategy.TablePerClass)
// TODO: StaticEntity heeft geen database entry, dit moeten we fixen of het concept "Static Entity" laten vallen
//public class Building extends StaticEntity
public class Building extends BaseRubyModel
{
	private int[] tids;

    @Transient
	private String geo;

	@Column
    private String name;

	@Column
	@ManyToOne(mapIdTo = "geo")
	private GameEntity owner;

	@ID(mapIdToEntity = "nationbuilder.lib.data.map.entities.PowerRelayStation")
	private ReferenceMapping power_relay_station;

	@ID(mapIdToEntity = "nationbuilder.lib.data.map.entities.WareHouse")
	private ReferenceMapping warehouse;

	@ID(mapIdToEntity = "nationbuilder.lib.data.map.entities.EnergyBuilding")
	private ReferenceMapping energybuilding;

	@ManyToOne(mapIdTo = MappedBy.SELF)
	private ReferenceMapping tile;

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
}
