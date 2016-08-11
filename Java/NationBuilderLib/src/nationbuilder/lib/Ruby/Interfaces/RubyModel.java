package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.orm.ID;

import java.io.Serializable;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyModel extends Saveable,Serializable {
     ID getId();
     boolean isCommitted();
     void setCommitted(boolean committed);
     void setId(ID id);
	 void FetchIDs();
     void setRubyContext(RubyContext context);

     boolean markDirty();
     void setDirty(boolean dirty);

}
