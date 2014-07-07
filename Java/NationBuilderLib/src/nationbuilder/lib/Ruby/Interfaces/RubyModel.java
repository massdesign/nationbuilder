package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.http.data.ID;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyModel extends Saveable {
    public ID getId();
    public void setId(ID id);
    public void setRubyContext(RubyContext context);

}
