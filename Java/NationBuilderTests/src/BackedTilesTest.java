import java.util.List;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.http.HttpRequest;
import nationbuilder.lib.http.data.HttpData;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by patrick on 10/20/14.
 */
public class BackedTilesTest
{
	RubyContext context;
	@Before
	public void setup()
	{
		context  =new RubyContextFactory().createRubyContext();
		HttpData resultCode = null;

		resultCode = HttpRequest.sendGetRequest("http://localhost:8083/resetdb");
		if(resultCode.getResponseCode() == 200)
		{
			Log.writeInfo("database reset successfull");
		}
	}
	@Test
	public void testGetAllTiles()
	{


	    RubyObjectFactory<Tile> mapTileFactory =  context.<Tile>createRubyObjectFacory(Tile.class,Tile[].class);
		//List<Tile> tiles = mapTileFactory.getAll();
		//Tile tile = mapTileFactory.get(1);
	}
}
