package nationbuilder.lib.data.map.mapservice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.configuration.RubyConfiguration;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;
import nationbuilder.lib.data.map.xml.RubyDIPropertyLoader;

public class MapServiceConnector {


	private String location;
    private RubyContext context;

	public MapServiceConnector(RubyContext context)
	{
		RubyDIPropertyLoader rubyDIPropertyLoader = new RubyDIPropertyLoader();
		rubyDIPropertyLoader.load(context.getApplicationContext());
        this.location =   rubyDIPropertyLoader.getRubyConfiguration().getBackend() + ":" + rubyDIPropertyLoader.getRubyConfiguration().getPort();

        this.context = context;
	}
    public void addMap(MapMap map)
    {

        try
		{
			context.SaveObject(map, "maps");

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
            context.SaveObject(layer, "layers");
        }
		catch (RubyException e) {
            Log.write(e,LogType.ERROR);
        }
	}
	public void   addDataset(MapDataset dataset)
	{
        this.addMap(dataset.getMap());

		for(Image image : dataset.getMapImages())
		{
			this.addImage(image);
		}
		for (Tile tile : dataset.getMapTiles())
		{
			try
			{
				context.SaveObject(tile, "tiles");
			}
			catch (RubyException e)
			{
				Log.write(e, LogType.ERROR);
				break;
			}
		}
        ArrayList<Layer> mapLayer = dataset.getMapLayers();
		// TODO: iher layer  gaan monitoren
		// Map reversen.. volgens mij
		for(int i=mapLayer.size()-1;i>=0;i--) {
			//this.addLayer(mapLayer.get(i));
			try
			{
				context.SaveObject(mapLayer.get(i), "layers");
			}
			catch (RubyException e)
			{
				Log.write(e, LogType.ERROR);
			}

		}

	}
	public void addImage(Image image)
	{
			image.getImageFile();
		try
		{
			image.Save("images");
		}
		catch (RubyException e)
		{
		  Log.write(e,LogType.ERROR);
		}
		image.getImageFile().Save("/uploads/");
	}

}
