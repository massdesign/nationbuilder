package nationbuilder.lib.Ruby;

import java.lang.reflect.Field;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Logging.LogType;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.Interfaces.RubyService;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.ID;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;
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

    public boolean store(ClassMap clazzMap,RubyModel model, String resourceUrl) throws PostRequestFailedException, ObjectPersistanceFailedException, MissingAnnotationException, ObjectConversionFailedException, IOException, ColumnNotFoundException {

        boolean result = false;
        if(model.getId() == null)
        {
            ResponseData data = this.rubyService.postObject(clazzMap, model, resourceUrl);
            // TODO: dit moet anders.. de structuur m.b.t ObjectBuilders is raar.. Er moet een manier gemaakt worden die de juiste Objectbuilder selecteert vanuit de service
            ID resultObject = this.objectBuilder.createIDFromResponse(data);
            resultObject.setType(model.getClass().getName());
            // We zijn er in geslaagd om het object ter persisteren.. (al dan niet naar de database of een tussenlaag) Dit betekend dat het object vanaf nu niet meer dirty is..
            model.setDirty(false);
            Entity entityAnnotation = model.getClass().getAnnotation(Entity.class);
            // Als we niet de root klasse zijn van een modelboom en we inheritstrategy is TablePerClass dan moeten we het ID opslaan in de overervende superklasse
            if (entityAnnotation != null && clazzMap.getSuperClassFromCurrent() != BaseRubyModel.class
                && entityAnnotation.strategy() == InhiritanceStrategy.TablePerClass)
            {
                Field idField = RubyAssociationResolver.getIDFromSuperClass(clazzMap, model);

                try
                {
                    idField.setAccessible(true);
                    ReferenceMapping resultMapping = new ReferenceMapping(resultObject, clazzMap.getCurrent());

                    idField.set(model, resultMapping);
                    result = true;
                }
                catch (IllegalAccessException e)
                {
                    Log.write(e, LogType.ERROR);
                }
            }
            else
            {
                model.setId(resultObject);
            }
        }
        return result;
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
