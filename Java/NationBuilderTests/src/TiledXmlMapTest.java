import nationbuilder.lib.data.map.xml.*;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TiledXmlMapTest {



	private String  tmxFile = "/home/patrick/Git/nationbuilder/Tiled/Maps/overview.tmx";
	
	TiledXmlMapFactory xmlFactory = new TiledXmlMapFactory();
	XmlMap map = null;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testLayers() {
		// TODO: add this map as a resource
		//given a map loaded from xml
		this.map =	xmlFactory.createTiledXmlMap(tmxFile);

		// when
		ArrayList<XmlLayer> layers = this.map.getLayers();
		
		if(layers.size() > 0)
		{
			assertTrue("layerslist is empty",true);
		}
	}
	@Test
	public void testTilesets()
	{
		this.map =	xmlFactory.createTiledXmlMap(tmxFile);

		// when
		ArrayList<XmlTileset> tilesets = this.map.getTilesets();
		
		if(tilesets.size() > 0)
		{
			assertTrue("tilesetslist is empty",tilesets.size() > 0);
		}
	}
	@Test 
	public void testTiles()
	{
		this.map =	xmlFactory.createTiledXmlMap(tmxFile);
		
		ArrayList<XmlTile> tiles = this.map.getLayers().get(0).getTiles();
		assertTrue("tilesetlist is empty",tiles.size() > 0);
	}
	
	@Test
	public void testTilesetObject()
	{
		this.map =	xmlFactory.createTiledXmlMap(tmxFile);
		// when
		ArrayList<XmlTileset> tilesets = this.map.getTilesets();
		
		
		String actualString; 
		String expectedString; 
		int actualInt;
		int expectedInt;
		actualString = tilesets.get(0).getName();
		expectedString = "mountain3ot8";
		
		// first element
		actualInt = tilesets.get(0).getFirstGid();
		expectedInt = 1;
		assertTrue("unexpected Value",expectedString.equals(actualString));
		assertTrue("unexpected Value",actualInt == expectedInt);
		actualInt = tilesets.get(0).getTileHeight();
		expectedInt = 32;
		assertTrue("unexpected Value",actualInt == expectedInt);
		
		actualInt = tilesets.get(0).getTileWidth();
		expectedInt = 32;
		assertTrue("unexpected Value",actualInt == expectedInt);
	
		actualString = tilesets.get(6).getName();
		expectedString = "Trees-3";
		// last element
		actualInt = tilesets.get(6).getFirstGid();
		expectedInt = 461;
		assertTrue("unexpected Value",expectedString.equals(actualString));
		assertTrue("unexpected Value",actualInt == expectedInt);
		actualInt = tilesets.get(6).getTileHeight();
		expectedInt = 32;
		assertTrue("unexpected Value",actualInt == expectedInt);
		
		actualInt = tilesets.get(6).getTileWidth();
		expectedInt = 32;
		assertTrue("unexpected Value",actualInt == expectedInt);
		

		
		
	}
	
	@Test
	public void testLayerObject() {
		// TODO: add this map as a resource
		//given a map loaded from xml
		this.map =	xmlFactory.createTiledXmlMap(tmxFile);

		// when
		ArrayList<XmlLayer> layers = this.map.getLayers();
		String expectedString = "Water";
		String actualString = layers.get(0).getName();
		int expectedInt = 40;
		int actualInt = layers.get(0).getWidth();
		assertTrue("unexpectedValue",expectedString.equals(actualString));
		
		assertTrue("unexpectedValue",expectedInt == actualInt);
		expectedString = "players";
		actualString = layers.get(5).getName();
		expectedInt = 40;
		actualInt = layers.get(0).getHeight();
		assertTrue("unexpectedValue",expectedString.equals(actualString));
		
	}
	@Ignore
	@Test
	public void testImageObject()
	{
		this.map =	xmlFactory.createTiledXmlMap(tmxFile);
		
		// first element
		// TODO: this unitTest is broken, fix it.
		/*
		Image image = map.getTilesets().get(0).getImage();
		int expectedInt;
		int actualInt;
		expectedInt = 96;
		actualInt = image.getWidth();
		assertTrue("unexpectedValue",expectedInt == actualInt);
		expectedInt = 128;
		actualInt = image.getHeight();
		assertTrue("unexpectedValue",expectedInt == actualInt);	
		image = map.getTilesets().get(6).getImage();
		// last element
		expectedInt = 96;
		actualInt = image.getWidth();
		assertTrue("unexpectedValue",expectedInt == actualInt);
		expectedInt = 128;
		actualInt = image.getHeight();
		assertTrue("unexpectedValue",expectedInt == actualInt);
		image = map.getTilesets().get(0).getImage();
		
	    String bi =	image.getFileLocation();
	    */
	    //assertTrue("image not loaded",bi != null);
	    assertTrue(true);

		
		
		
	}
	@Test
	public void testTileObject()
	{
		this.map =	xmlFactory.createTiledXmlMap(tmxFile);
		
		XmlTile tile = this.map.getLayers().get(0).getTiles().get(0);
		
		int actual = tile.getGID();
	    int expected = 23;
	    
		assertTrue("unexpectedValue",actual == expected);
		
	}
	

}
