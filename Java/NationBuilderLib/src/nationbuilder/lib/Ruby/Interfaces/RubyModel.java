package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.orm.ID;

import java.io.Serializable;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyModel extends Saveable,Serializable {
    public ID getId();
    public boolean isCommitted();
    public void setCommitted(boolean committed);
    public void setId(ID id);
	public void FetchIDs();
    public void setRubyContext(RubyContext context);

}
