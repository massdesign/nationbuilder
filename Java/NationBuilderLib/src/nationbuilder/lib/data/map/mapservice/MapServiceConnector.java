package nationbuilder.lib.data.map.mapservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import com.google.gson.Gson;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.http.JsonServiceConnector;
import nationbuilder.lib.http.data.HttpData;
import nationbuilder.lib.http.data.ID;

public class MapServiceConnector {


	private String location;
	private JsonServiceConnector jsonServiceConnector;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}


	public MapServiceConnector(String location)
	{
		this.jsonServiceConnector = new JsonServiceConnector(location);
	}

	public void addTile(MapTile tile)
	{
		try
		{
			Gson gson = new Gson();

			tile.fetchIDs();
			HttpData data = this.jsonServiceConnector.postObject(tile, "/tiles/");

		    ID resultObject =	gson.fromJson(data.getBody(), ID.class);

		    tile.setId(resultObject);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
    public void addMap(MapMap map)
    {
        Gson gson = new Gson();

        try {
            HttpData data = this.jsonServiceConnector.postObject(map,"/maps/");
            ID resultObject  =  gson.fromJson(data.getBody(),ID.class);
            map.setId(resultObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public void addLayer(MapLayer layer)
	{
		Gson gson = new Gson();
		layer.fetchIDs();
		try {
			HttpData data = this.jsonServiceConnector.postObject(layer, "/layers/");
			ID resultObject = gson.fromJson(data.getBody(), ID.class);
			layer.setId(resultObject);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void addDataset(MapDataset dataset)
	{
        this.addMap(dataset.getMap());
	//	this.addImage(dataset.getMapImages().get(0));
		for(MapImage image : dataset.getMapImages())
		{
			this.addImage(image);
		}
        Iterator it = dataset.getMapLayers().entrySet().iterator();

        ArrayList<MapLayer> mapLayer = new ArrayList<MapLayer>();

		while(it.hasNext())
		{
			Map.Entry<String, MapLayer> pair = (Map.Entry<String, MapLayer>)it.next();
            mapLayer.add(pair.getValue());
			 //this.addLayer(pair.getValue());
		}

        ListIterator<MapLayer> li = mapLayer.listIterator(mapLayer.size());

        while(li.hasPrevious())
        {
            this.addLayer(li.previous());
        }

		for(MapTile tile : dataset.getMapTiles())
		{
			this.addTile(tile);
		}
	}
	public int addImage(MapImage image)
	{
		int resultId = 0;
		try {
			image.getImageFile();
			image.fetchIDs();
			HttpData dataObject = this.jsonServiceConnector.postObject(image, "/images/");

			Gson gson = new Gson();
		    ID resultObject =	gson.fromJson(dataObject.getBody(), ID.class);
		     image.setId(resultObject);
			int fileStatusCode = this.jsonServiceConnector.postFile(image.getImageFile(), "/uploads/");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return resultId;
	}

}
