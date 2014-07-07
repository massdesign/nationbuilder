package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyService;

/**
 * Created by patrick on 7/8/14.
 */
public class RubyContext {

    public RubyService getRubyService() {
        return rubyService;
    }

    public void setRubyService(RubyService rubyService) {
        this.rubyService = rubyService;
    }

    private RubyService rubyService;

    public<T extends RubyModel> T createRubyModel(T model)
    {
        model.setRubyContext(this);
        return model;
    }

}
