package nationbuilder.lib.Ruby.Interfaces;

import java.util.List;
import nationbuilder.lib.Ruby.RubyContext;

/**
 * Created by patrick on 10/21/14.
 */
public interface RubyObjectFactory<T extends RubyModel>
{
	public void setRubyContext(RubyContext context);
	public T get(int id);
	public List<T> getAll();
}
