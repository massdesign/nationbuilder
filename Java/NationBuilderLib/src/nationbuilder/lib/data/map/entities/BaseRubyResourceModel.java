package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.orm.BaseRubyModel;

import java.io.File;

/**
 * Created by patrick on 7/8/14.
 */
public class BaseRubyResourceModel extends BaseRubyModel {

    private File resource;
    public File getResource()
    {
        return resource;
    }
    public void setResource(File resource)
    {
        this.resource = resource;
    }
    private Tile maptile;

    @Override
    public boolean Save(String resourceUrl)
    {
        return this.context.SaveResource(this, resourceUrl);
    }
}
