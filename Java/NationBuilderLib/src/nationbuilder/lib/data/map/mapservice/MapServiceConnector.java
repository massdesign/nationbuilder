package nationbuilder.lib.data.map.mapservice;

import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
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
}
