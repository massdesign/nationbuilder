package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "warehouses",strategy = InhiritanceStrategy.TablePerClass)
public class WareHouse extends Building
{
	// Building for the sake of being a building.. Maybe have so purpose later on..
	public WareHouse()
	{

	}
}
