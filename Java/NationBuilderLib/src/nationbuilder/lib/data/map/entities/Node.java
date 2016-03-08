package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.ID;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.Transient;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "node_types",strategy = InhiritanceStrategy.TablePerClass)
public class Node extends BaseRubyModel
{
    // TODO: zelfde reden als building.name.. heeft te maken met superclassing en subclassing wat nog niet goed geregeld is in de RubyDI
    private String name;

	private boolean destroyable;

	@ID()
	private ReferenceMapping power_grid_node;

	public boolean isDestroyable()
	{
		return destroyable;
	}

	public void setDestroyable(boolean destroyable)
	{
		this.destroyable = destroyable;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
