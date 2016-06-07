package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class MapDataset {

	
	private ArrayList<Image> mapImages;
	private ArrayList<Tile> mapTiles;
	private ArrayList<Layer> mapLayers;

    private ArrayList<Resource> resources;

    public MapMap getMap() {
        return map;
    }

    public void setMap(MapMap map) {
        this.map = map;
    }

    private MapMap map;

	public ArrayList<Image> getMapImages() {
		return mapImages;
	}
	public void setMapImages(ArrayList<Image> mapImages) {
		this.mapImages = mapImages;
	}
	public ArrayList<Tile> getMapTiles() {
		return mapTiles;
	}
	public void setMapTiles(ArrayList<Tile> mapTiles) {
		this.mapTiles = mapTiles;
	}
    public ArrayList<Resource> getResources() {
        return resources;
    }

	public ArrayList<Layer> getMapLayers()
	{
		return mapLayers;
	}

	public void setMapLayers(ArrayList<Layer> mapLayers)
	{
		this.mapLayers = mapLayers;
	}

    /*public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }*/

}
