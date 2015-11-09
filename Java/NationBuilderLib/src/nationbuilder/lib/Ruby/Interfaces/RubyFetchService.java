package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.http.data.HttpResponseData;

/**
 * Created by patrick on 12/12/14.
 */
public interface RubyFetchService
{
	HttpResponseData getObject(String resourceUrl);
}
