package World;

import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.City;
import nationbuilder.lib.data.map.entities.WareHouse;

/**
 * Created by patrick on 3/8/15.
 */
public class WarehouseFiller extends BaseFiller {

    RubyContext context;
    private RubyObjectFactory<City> cityRubyObjectFactory;
    public WarehouseFiller(RubyContext context)
    {
        super(context);
        this.context = context;
        this.cityRubyObjectFactory = this.getContext().createRubyObjectFacory(City.class,City[].class);
    }

    @Override
    public void Fill() {


        WareHouse wareHouse = createWareHouse("Standard warehouse");
        City cityOwner = this.cityRubyObjectFactory.get(1);
        wareHouse.setOwner(cityOwner);
        this.getRubyModels().add(wareHouse);

    }

    private WareHouse createWareHouse(String name)
    {
       WareHouse result =  this.getContext().createRubyModel(WareHouse.class);

        result.setName(name);

        return result;
    }

}
