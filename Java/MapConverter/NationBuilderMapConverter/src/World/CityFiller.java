package World;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.City;

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

	}


	private City createCity(String name)
	{
		City result = new City();

		result.setName(name);

		return result;
	}
}
