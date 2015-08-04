package nationbuilder.lib.data.map.mapservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.NoAttachedRubyContextException;
import nationbuilder.lib.Ruby.Exceptions.ObjectPersistanceFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.RubyConfiguration;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.http.JsonServiceConnector;

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
    public void addMap(MapMap map)
    {

        try
		{
			context.SaveObject(map, "/maps/");

		}
		catch (RubyException e)
		{
			Log.write(e, LogType.ERROR);
		}

	}
    public void addResource(Resource resource)
    {
        try
        {
            context.SaveObject(resource,"/resources/");
        }
		catch (RubyException e)
        {
            Log.write(e,LogType.ERROR);
        }
    }
	public void addLayer(Layer layer)
	{
        try {
            context.SaveObject(layer, "/layers/");
        }
		catch (RubyException e) {
            Log.write(e,LogType.ERROR);
        }
	}
	public void   addDataset(MapDataset dataset)
	{
        this.addMap(dataset.getMap());

       /* for(Resource resource : dataset.getResources())
        {
            this.addResource(resource);
        } */

		for(MapImage image : dataset.getMapImages())
		{
			this.addImage(image);
		}
        Iterator it = dataset.getMapLayers().entrySet().iterator();

        ArrayList<Layer> mapLayer = new ArrayList<Layer>();

		while(it.hasNext())
		{
			Map.Entry<String, Layer> pair = (Map.Entry<String, Layer>)it.next();
            mapLayer.add(pair.getValue());
		}

        ListIterator<Layer> li = mapLayer.listIterator(mapLayer.size());

        while(li.hasPrevious())
        {
            this.addLayer(li.previous());
        }

		for(Tile tile : dataset.getMapTiles())
		{
			try
			{
				context.SaveObject(tile,"/tiles/");
			}
			catch (RubyException e)
			{
				Log.write(e,LogType.ERROR);
			}
		}


	}
	public void addImage(MapImage image)
	{
			image.getImageFile();
			//image.fetchIDs();
		try
		{
			image.Save("/images/");
		}
		catch (RubyException e)
		{
		  Log.write(e,LogType.ERROR);
		}
		image.getImageFile().Save("/uploads/");
	}

}
