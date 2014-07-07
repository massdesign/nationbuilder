package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.ID;

/**
 * Created by patrick on 7/8/14.
 */
public class BaseRubyModel implements RubyModel {
    private ID id;
    private RubyContext context;
    @Override
    public ID getId() {
        return id;
    }

    @Override
    public void setId(ID id) {
    this.id = id;
    }

    @Override
    public void setRubyContext(RubyContext context) {
    this.context = context;
    }

    @Override
    public boolean Save() {
        return false;
    }
}
