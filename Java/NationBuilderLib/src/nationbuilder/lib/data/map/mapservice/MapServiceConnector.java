package nationbuilder.lib.data.map.mapservice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import com.google.gson.Gson;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.RubyConfiguration;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.http.JsonServiceConnector;
import nationbuilder.lib.http.data.HttpData;
import nationbuilder.lib.http.data.ID;

public class MapServiceConnector {


	private String location;
	private RubyService jsonServiceConnector;
    private RubyContext context;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}


	public MapServiceConnector(RubyContext context)
	{
        this.location =  RubyConfiguration.RubyBackend + ":" + RubyConfiguration.RubyBackendPort;
		this.jsonServiceConnector = new JsonServiceConnector(location);
        this.context = context;
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

        try {
            context.SaveObject(map,"/maps/");
        } catch (IOException e) {
            Log.write(e, LogType.ERROR);
        }

    }
	public void addLayer(MapLayer layer)
	{
		layer.fetchIDs();
        try {
            context.SaveObject(layer,"/layers/");
        } catch (IOException e) {
            Log.write(e,LogType.ERROR);
        }
	}
	public void addDataset(MapDataset dataset)
	{
        this.addMap(dataset.getMap());
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
	public void addImage(MapImage image)
	{
		int resultId = 0;
			image.getImageFile();
			image.fetchIDs();
            image.Save("/images/");
            image.getImageFile().Save("/uploads/");
	}

}
