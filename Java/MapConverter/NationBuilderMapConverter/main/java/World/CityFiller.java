package World;

import nationbuilder.lib.Ruby.Exceptions.RubyException;
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
        city1.setPopulation(300);
       City city2 = createCity("Paris");
        city2.setPopulation(900);
       City city3 = createCity("Sankt Augustin");
        city3.setPopulation(12300);


		try {
			city1.Save();
			city2.Save();
			city3.Save();
		}
		catch (RubyException e)
		{
			e.printStackTrace();
		}


        this.getRubyModels().add(city1);
        this.getRubyModels().add(city2);
        this.getRubyModels().add(city3);
	}



	private City createCity(String name)
	{
		City result = this.getContext().createRubyModel(City.class);

		result.setName(name);

		return result;
	}
}
