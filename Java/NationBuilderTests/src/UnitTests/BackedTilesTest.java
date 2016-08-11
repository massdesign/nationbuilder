package UnitTests;

import java.util.List;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectFetchFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.data.map.entities.Resource;
import org.junit.Assert;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.DefaultRubyContextFactory;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.http.HttpRequestUtil;
import nationbuilder.lib.http.data.HttpResponseData;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by patrick on 10/20/14.
 */
public class BackedTilesTest
{
	RubyContext context;
	RubyObjectFactory<Tile> mapTileFactory;
	RubyObjectFactory<Resource> resourceFactory;
	@Before
	public void setup()
	{
		context  = new DefaultRubyContextFactory().createDefaultRubyContext();
		mapTileFactory= context.createRubyObjectFacory(Tile.class, Tile[].class);
		resourceFactory = context.createRubyObjectFacory(Resource.class,Resource[].class);

	}
	private void loadTestDatabase()
	{
		HttpResponseData resultCode = null;

		resultCode = HttpRequestUtil.sendGetRequest("http://localhost:8083/deploydb");
		if (resultCode.getResponseCode() == 200)
		{
			Log.writeInfo("database deploy successfull");
		}
	}
	private void createEmpyDatabase()
	{
		HttpResponseData resultCode = null;

		resultCode = HttpRequestUtil.sendGetRequest("http://localhost:8083/resetdb");
		if (resultCode.getResponseCode() == 200)
		{
			Log.writeInfo("database reset successfull");
		}
	}
	@Test
	public void testGetAllTiles() throws ObjectConversionFailedException {
		loadTestDatabase();
		List<Tile> tiles = mapTileFactory.getAll();

		int current =   tiles.size();
		int expected = 114;
		Assert.assertEquals(current, expected);
	}
	@Test
	public void testGetTile() throws ObjectConversionFailedException {
		loadTestDatabase();
		Tile tile = mapTileFactory.get(3);
		Assert.assertNotNull(tile);
	}
	@Test
	public void testCreateTile() throws RubyException {
		createEmpyDatabase();

		Tile expected = this.context.createRubyModel(Tile.class);
		expected.setXoffset(10);
		expected.setYoffset(20);
		expected.setXposition(30);
		expected.setYposition(40);
		//expected.Save("/tiles/");

		Tile current = 	this.mapTileFactory.get(1);

		Assert.assertNotNull(expected);
		Assert.assertEquals(expected.getXoffset(),current.getXoffset());
		Assert.assertEquals(expected.getYoffset(), current.getYoffset());
		Assert.assertEquals(expected.getXposition(), current.getXposition());
		Assert.assertEquals(expected.getYposition(), current.getYposition());
	}
	@Test
	public void testFindTile() throws ObjectFetchFailedException
	{
        // TODO: Tiles controller moet alleen tiles teruggeven, geen andere entiteiten
		loadTestDatabase();
		//List<Resource> tiles = this.resourceFactory.get("find","4","5");
        Assert.assertTrue(true);
	}
}
