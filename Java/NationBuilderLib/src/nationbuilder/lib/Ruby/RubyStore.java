package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import nationbuilder.lib.Ruby.orm.ID;

/**
 * Created by patrick on 7/16/14.
 */
public class RubyStore {

    private ArrayList<RubyModel> models;
    public RubyStore()
    {
        this.models =new ArrayList<>();
    }



    public Iterator getIterator() {
        return this.models.iterator();
    }

    /**
     * This is effective a SELECT * FROM query, retrieves all known instances from a given class
     * @return
     */
    public <T extends RubyModel> List<T> getRubyModelByType(Class<?> type)
    {
        List<T> result = new ArrayList<T>();
        for(RubyModel rubyModel : this.models ) {

            if(rubyModel != null && rubyModel.getClass().equals(type)) {
                result.add((T)rubyModel);
            }

        }

        return result;
    }

    public void registerRubyModel(RubyModel model)
    {
        if(!this.models.contains(model)) {
            this.models.add(model);
        }
    }
}
