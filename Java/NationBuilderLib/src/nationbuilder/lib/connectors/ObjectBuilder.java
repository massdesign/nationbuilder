package nationbuilder.lib.connectors;

import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.data.map.ResponseData;

/**
 * Created by patrick on 12/11/14.
 */
public interface ObjectBuilder
{
	public Object createObjectFromString(ResponseData data, Class<?> clazz) throws ObjectConversionFailedException;
	String createStringFromObject(Object object) throws ObjectConversionFailedException, MissingAnnotationException;
}
