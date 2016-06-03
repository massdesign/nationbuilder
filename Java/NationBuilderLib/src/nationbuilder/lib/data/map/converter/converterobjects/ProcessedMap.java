package nationbuilder.lib.data.map.converter.converterobjects;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.data.map.xml.XmlHelper;

/**
 * @author patrick.ekkel
 */
public class ProcessedMap
{
	public ProcessedMap() {
		this.processedTilesets = new ArrayList<>();
	}
	private List<ProcessedTileset> processedTilesets;
	private String version;
	private int width;
	private int height;
	private int tileheight;
	private int tilewidth;
	private int nextobjectid;


	public void add(ProcessedTileset processedTileset) {
		this.processedTilesets.add(processedTileset);
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getTileheight()
	{
		return tileheight;
	}

	public void setTileheight(int tileheight)
	{
		this.tileheight = tileheight;
	}

	public int getTilewidth()
	{
		return tilewidth;
	}

	public void setTilewidth(int tilewidth)
	{
		this.tilewidth = tilewidth;
	}

	public int getNextobjectid()
	{
		return nextobjectid;
	}

	public void setNextobjectid(int nextobjectid)
	{
		this.nextobjectid = nextobjectid;
	}

	public void load() {

		XmlHelper.getInt(this.map, "height");
		XmlHelper.getInt(this.map, "tilewidth");
		XmlHelper.getInt(this.map, "tileheight");

	}

}
