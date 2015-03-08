package World;

import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.City;
import nationbuilder.lib.data.map.entities.State;
import nationbuilder.lib.data.map.entities.WareHouse;

/**
 * @author patrick.ekkel
 */
public class CityFiller extends BaseFiller
{
	public CityFiller(RubyContext context)
	{
		super(context);
	}

	@Override
	public void Fill()
	{



       City city1 = createCity("Utrecht");
       City city2 = createCity("Paris");
       City city3 = createCity("Sankt Augustin");


        this.getRubyModels().add(city1);
        this.getRubyModels().add(city2);
        this.getRubyModels().add(city3);



	}


	private City createCity(String name)
	{
		City result = new City();

		result.setName(name);

		return result;
	}
}
