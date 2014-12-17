package nationbuilder.lib.connectors;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.http.data.ResponseData;

/**
 * Created by patrick on 12/15/14.
 */
public class SqlObjectBuilder implements ObjectBuilder
{
    @Override
    public Object createObjectFromString(ResponseData data, Class<?> clazz) throws ObjectConversionFailedException {
        return null;
    }

    @Override
    public String createStringFromObject(Object object) {
        return null;
    }
}
