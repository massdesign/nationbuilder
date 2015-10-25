import nationbuilder.lib.Ruby.Association.annotation.MappingInfo;
import nationbuilder.lib.Ruby.OneToOneStrategy;
import nationbuilder.lib.data.map.entities.Resource;
import nationbuilder.lib.data.map.entities.Tile;
import org.junit.Test;

/**
 * @author patrick.ekkel
 */
public class ResolveStrategyTest
{

	@Test
	public void oneToOneStrategyTest() throws NoSuchFieldException, IllegalAccessException
	{


		MappingInfo mappingInfo = new MappingInfo("tile", Resource.class,new Tile(),null);

		OneToOneStrategy oneToOneStrategy = new OneToOneStrategy("OneToOne Resolving Strategy");
		oneToOneStrategy.setMappingInfo(mappingInfo);
		oneToOneStrategy.resolve();
	}
	@Test
	public void oneToManyStrategyTest() {

	}
}
