package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.orm.ID;
import nationbuilder.lib.connectors.ObjectBuilder;
import nationbuilder.lib.data.map.ResponseData;

import java.io.IOException;
import java.util.List;

/**
 * Created by patrick on 6/13/15.
 */
public class RubyObjectManager {

    private RubyService rubyService;
    private RubyStore rubyStore;
    private ObjectBuilder objectBuilder;

    public RubyObjectManager(RubyService rubyService, RubyStore rubyStore, ObjectBuilder objectBuilder) {


        this.rubyService = rubyService;
        this.rubyStore = rubyStore;
        this.objectBuilder = objectBuilder;

    }

    public boolean store(RubyModel model, String resourceUrl) throws PostRequestFailedException, ObjectPersistanceFailedException, MissingAnnotationException, ObjectConversionFailedException, IOException, ColumnNotFoundException {

        ResponseData data = this.rubyService.postObject(model,resourceUrl);
        // TODO: dit moet anders.. de structuur m.b.t ObjectBuilders is raar.. Er moet een manier gemaakt worden die de juiste Objectbuilder selecteert vanuit de service
            ID resultObject = (ID)this.objectBuilder.createObjectFromString(data, ID.class);
            resultObject.setType(model.getClass().getName());
            model.setId(resultObject);
            this.rubyStore.registerRubyModel(model);

        return resultObject != null ? true : false;
    }

    public RubyModel retrieve(Class clazz,ObjectSelector selector) throws ObjectConversionFailedException {
        // first look in the ruby store, then look up external sources
        List<RubyModel> models =  this.rubyStore.getRubyModelByType(clazz);
        RubyModel result = null;
        switch (selector) {
            case First:
                if(models != null && models.size() > 0)
                {
                   result = models.get(0);
                }
                else {
                    ResponseData data = this.rubyService.getObject("/" +  RubyRESTHelper.pluralize(clazz.getSimpleName()).toLowerCase() + "/first");
                    if (data != null)
                    {
                        result = (RubyModel)this.objectBuilder.createObjectFromString(data, clazz);
                    }
                }

        }
        return result;
    }
}
