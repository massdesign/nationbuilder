package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.http.JsonServiceConnector;

/**
 * Created by patrick on 7/10/14.
 */
public class RubyContextFactory {


    public RubyContext createRubyContext()
    {
        RubyService service = new JsonServiceConnector(String.format("",RubyConfiguration.RubyBackend,RubyConfiguration.RubyBackendPort));
        RubyContext result = new RubyContext(service);
        return result;
    }
}
