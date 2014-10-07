package World;

import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Regime;

/**
 * Created by patrick on 10/6/14.
 */
public class RegimeFiller extends BaseFiller
{
	public RegimeFiller(RubyContext context)
	{
		super(context);
	}

	private Regime createRegime(String name)
	{
		Regime result = new Regime();
		result.setName(name);

		return result;
	}
	@Override
	public void Fill()
	{

		this.getRubyModels().add(createRegime("Republic"));
		this.getRubyModels().add(createRegime("Kingdom"));
		this.getRubyModels().add(createRegime("Empire"));
		this.getRubyModels().add(createRegime("Commonwealth"));
		this.getRubyModels().add(createRegime("Federation"));
		this.getRubyModels().add(createRegime("Colony"));
		this.getRubyModels().add(createRegime("Principality"));
		this.getRubyModels().add(createRegime("Protectorate"));
		this.getRubyModels().add(createRegime("United States"));
		this.getRubyModels().add(createRegime("United Kingdom"));
		this.getRubyModels().add(createRegime("Peoples republic"));
		this.getRubyModels().add(createRegime("Democratic republic"));
		this.getRubyModels().add(createRegime("Confederacy"));
		this.getRubyModels().add(createRegime("Sultanate"));
		this.getRubyModels().add(createRegime("Dominion"));
		this.getRubyModels().add(createRegime("Holy Empire"));
		this.getRubyModels().add(createRegime("Free land"));
		this.getRubyModels().add(createRegime("Constitutional Monarchy"));
		this.getRubyModels().add(createRegime("Community"));
		this.getRubyModels().add(createRegime("Rogue Nation"));
	}
}
