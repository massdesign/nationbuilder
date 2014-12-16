package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by patrick on 7/16/14.
 */
public class RubyStore {

    private HashMap<ID,RubyModel> modelHashMap;
    public RubyStore()
    {
        this.modelHashMap =new HashMap<ID, RubyModel>();
    }

    public RubyModel getRubyModelbyId(ID id)
    {
        RubyModel result = null;
        if(modelHashMap.containsKey(id))
        {
            result = modelHashMap.get(id);
        }
        else
        {
            // TODO: retrieve the object from the service
            // TODO: implement when needed
        }
        return result;
    }

    /**
     * This is effective a SELECT * FROM query, retrieves all known instances from a given class
     * @return
     */
    public <T extends RubyModel> List<T> getRubyModelByType(Class<?> type)
    {
        List<T> result = new ArrayList<T>();
        for(ID id : modelHashMap.keySet())
        {
            if(type.getName().equals(id.getType()))
            {
                result.add((T)modelHashMap.get(id));
            }
        }
        return result;
    }

    public void registerRubyModel(RubyModel model)
    {
        if(!modelHashMap.containsKey(model.getId()))
        {
            this.modelHashMap.put(model.getId(),model);
        }
    }
}
