package nationbuilder.lib.Ruby.Interfaces;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.http.data.ID;

import java.io.Serializable;

/**
 * Created by patrick on 7/8/14.
 */
public interface RubyModel extends Saveable,Serializable {
    public ID getId();
    public void setId(ID id);
    public void setRubyContext(RubyContext context);

}