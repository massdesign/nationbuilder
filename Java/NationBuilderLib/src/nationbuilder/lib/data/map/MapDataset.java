package nationbuilder.lib.data.map;

import java.util.ArrayList;
import java.util.HashMap;

public class MapDataset {

	
	private ArrayList<MapImage> mapImages;
	private ArrayList<MapTile> mapTiles;
	private HashMap<String,MapLayer> mapLayers;

    public MapMap getMap() {
        return map;
    }

    public void setMap(MapMap map) {
        this.map = map;
    }

    private MapMap map;
	public HashMap<String, MapLayer> getMapLayers() {
		return mapLayers;
	}
	public void setMapLayers(HashMap<String, MapLayer> mapLayers) {
		this.mapLayers = mapLayers;
	}
	public ArrayList<MapImage> getMapImages() {
		return mapImages;
	}
	public void setMapImages(ArrayList<MapImage> mapImages) {
		this.mapImages = mapImages;
	}
	public ArrayList<MapTile> getMapTiles() {
		return mapTiles;
	}
	public void setMapTiles(ArrayList<MapTile> mapTiles) {
		this.mapTiles = mapTiles;
	}

}
