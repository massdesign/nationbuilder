package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.HashMap;

public class MapDataset {

	
	private ArrayList<MapImage> mapImages;
	private ArrayList<Tile> mapTiles;
	private HashMap<String,Layer> mapLayers;

    private ArrayList<Resource> resources;

    public MapMap getMap() {
        return map;
    }

    public void setMap(MapMap map) {
        this.map = map;
    }

    private MapMap map;
	public HashMap<String, Layer> getMapLayers() {
		return mapLayers;
	}
	public void setMapLayers(HashMap<String, Layer> mapLayers) {
		this.mapLayers = mapLayers;
	}
	public ArrayList<MapImage> getMapImages() {
		return mapImages;
	}
	public void setMapImages(ArrayList<MapImage> mapImages) {
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

    /*public void setResources(ArrayList<Resource> resources) {
        this.resources = resources;
    }*/

}
