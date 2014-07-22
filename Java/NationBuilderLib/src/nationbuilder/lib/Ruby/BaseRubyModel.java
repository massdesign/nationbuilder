package nationbuilder.lib.Ruby;

import com.google.gson.annotations.Expose;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.ID;

import java.io.IOException;

/**
 * Created by patrick on 7/8/14.
 */
public class BaseRubyModel implements RubyModel {
    private ID id;
    @Expose
    protected RubyContext context;
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
    public boolean Save(String ResourceUrl)
    {
        try {
           return context.SaveObject(this,ResourceUrl);
        } catch (IOException e) {
            Log.write(ResourceUrl, LogType.ERROR);
        }

        return false;
    }
}
