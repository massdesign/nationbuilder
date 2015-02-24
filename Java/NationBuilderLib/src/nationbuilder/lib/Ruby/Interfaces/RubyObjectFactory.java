package nationbuilder.lib.Ruby.Interfaces;

import java.util.List;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.ObjectFetchFailedException;
import nationbuilder.lib.Ruby.RubyContext;

/**
 * Created by patrick on 10/21/14.
 */
public interface RubyObjectFactory<T extends RubyModel>
{
	public void setRubyContext(RubyContext context);
	public T get(int id) throws ObjectConversionFailedException;
	public List<T> getAll() throws ObjectConversionFailedException;
	public List<T> get(String action, String ... args) throws ObjectFetchFailedException, ObjectConversionFailedException;
	public T getFirst() throws ObjectConversionFailedException;
}
