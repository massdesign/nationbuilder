import java.util.List;
import nationbuilder.lib.data.map.entities.Layer;
import nationbuilder.lib.data.map.entities.Resource;
import nationbuilder.lib.data.map.xml.XmlLayer;
import org.junit.Assert;
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
	RubyObjectFactory<Tile> mapTileFactory;
	@Before
	public void setup()
	{
		context  =new RubyContextFactory().createRubyContext();
		mapTileFactory= context.<Tile>createRubyObjectFacory(Tile.class, Tile[].class);

	}
	private void loadTestDatabase()
	{
		HttpData resultCode = null;

		resultCode = HttpRequest.sendGetRequest("http://localhost:8083/deploydb");
		if (resultCode.getResponseCode() == 200)
		{
			Log.writeInfo("database deploy successfull");
		}
	}
	private void createEmpyDatabase()
	{
		HttpData resultCode = null;

		resultCode = HttpRequest.sendGetRequest("http://localhost:8083/resetdb");
		if (resultCode.getResponseCode() == 200)
		{
			Log.writeInfo("database reset successfull");
		}
	}
	@Test
	public void testGetAllTiles()
	{
		loadTestDatabase();
		List<Tile> tiles = mapTileFactory.getAll();

		int current =   tiles.size();
		int expected = 114;
		Assert.assertEquals(current, expected);
	}
	@Test
	public void testGetTile()
	{
		loadTestDatabase();
		Tile tile = mapTileFactory.get(3);
		Assert.assertNotNull(tile);
	}
	@Test
	public void testCreateTile()
	{
		createEmpyDatabase();

		Tile expected = this.context.createRubyModel(Tile.class);
		expected.setXoffset(10);
		expected.setYoffset(20);
		expected.setXposition(30);
		expected.setYposition(40);
		expected.Save("/tiles/");

		Tile current = 	this.mapTileFactory.get(1);

		Assert.assertNotNull(expected);
		Assert.assertEquals(expected.getXoffset(),current.getXoffset());
		Assert.assertEquals(expected.getYoffset(), current.getYoffset());
		Assert.assertEquals(expected.getXposition(), current.getXposition());
		Assert.assertEquals(expected.getYposition(), current.getYposition());
	}

}
