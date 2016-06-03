package nationbuilder.lib.data.map.converter.converterobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author patrick.ekkel
 */
public class ProcessedTileset
{

	private int firstgid;
	private String name;
	private int tilewidth;
	private int Tileheight;

	private List<ProcessedTileDefinition> processedTileDefinitions;
	private List<ProcessedImage> processedImages;

	public ProcessedTileset() {
		this.processedTileDefinitions = new ArrayList<>();
	}

	public List<ProcessedTileDefinition> getProcessedTileDefinitions()
	{
		return processedTileDefinitions;
	}

	public void setProcessedTileDefinitions(List<ProcessedTileDefinition> processedTileDefinitions)
	{
		this.processedTileDefinitions = processedTileDefinitions;
	}

	public void addProcessedTileDefinition(ProcessedTileDefinition processedTileDefinition) {
		this.processedTileDefinitions.add(processedTileDefinition);
	}
	public void addProcessedImage(ProcessedImage processedImage) {
		this.processedImages.add(processedImage);
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}


	public int getFirstgid()
	{
		return firstgid;
	}

	public void setFirstgid(int firstgid)
	{
		this.firstgid = firstgid;
	}

	public int getTileheight()
	{
		return Tileheight;
	}

	public void setTileheight(int tileheight)
	{
		Tileheight = tileheight;
	}

	public int getTilewidth()
	{
		return tilewidth;
	}

	public void setTilewidth(int tilewidth)
	{
		this.tilewidth = tilewidth;
	}
}
