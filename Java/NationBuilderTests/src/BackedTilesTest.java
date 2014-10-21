import java.util.List;
import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.data.map.entities.Tile;
import org.junit.Test;

/**
 * Created by patrick on 10/20/14.
 */
public class BackedTilesTest
{
	@Test
	public void testGetAllTiles()
	{
		RubyContext context = new RubyContextFactory().createRubyContext();

	    RubyObjectFactory<Tile> mapTileFactory =  context.<Tile>createRubyObjectFacory(Tile.class,Tile[].class);

		//List<Tile> tiles = mapTileFactory.getAll();

		Tile tile = mapTileFactory.get(1);



	}
}
