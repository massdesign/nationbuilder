package nationbuilder.lib.Ruby.Interfaces;

import java.io.IOException;
import nationbuilder.lib.data.map.entities.BaseRubyResourceModel;

/**
 * Created by patrick on 1/7/15.
 */
public interface RubyBlobService
{
	public int postFile(String fileLocation, String resourceUrl) throws IOException;
	public int postFile(BaseRubyResourceModel model, String resourceUrl) throws IOException;

}
