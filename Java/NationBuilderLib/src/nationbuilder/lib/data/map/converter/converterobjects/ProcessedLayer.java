package nationbuilder.lib.data.map.converter.converterobjects;

import java.util.ArrayList;
import java.util.List;

/**
 * @author patrick.ekkel
 */
public class ProcessedLayer
{
	private String name;
	private int width;
	private int height;

	private List<ProcessedTile> tiles;


	public ProcessedLayer() {

	this.tiles = new ArrayList<>();
	}

	public void addTile(ProcessedTile tile) {
		this.tiles.add(tile);

	}
}
