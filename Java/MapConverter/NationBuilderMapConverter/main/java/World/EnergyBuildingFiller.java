package World;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.EnergyBuilding;
import nationbuilder.lib.data.map.entities.EnergyBuildingType;

/**
 * Created by patrick on 10/7/14.
 */
public class EnergyBuildingFiller extends BaseFiller
{
	private List<EnergyBuildingType> energyBuildingTypes;
	public EnergyBuildingFiller(RubyContext context)
	{
		super(context);
		this.energyBuildingTypes = new ArrayList<EnergyBuildingType>();
	}

	@Override
	public void Fill()
	{
		fillEnergyBuildingTypes();
		fillEnergyBuildings();
	}

	private void fillEnergyBuildingTypes()
	{
		this.getRubyModels().add(createEnergyBuildingType("Nuclear power plant MK 1", 400, "Nuclear"));
		this.getRubyModels().add(createEnergyBuildingType("Nuclear power plant MK 2", 1000, "Nuclear"));
		this.getRubyModels().add(createEnergyBuildingType("Nuclear power plant MK 3", 3550, "Nuclear"));
		this.getRubyModels().add(createEnergyBuildingType("Nuclear power plant MK 4", 6520, "Nuclear"));
		this.getRubyModels().add(createEnergyBuildingType("Nuclear power plant MK 5", 7100, "Nuclear"));
		this.getRubyModels().add(createEnergyBuildingType("Gas Turbine Generator MK 1", 30, "Natural gas"));
		this.getRubyModels().add(createEnergyBuildingType("Gas Turbine Generator MK 2", 100, "Natural gas"));
		this.getRubyModels().add(createEnergyBuildingType("Gas Turbine Generator MK 3", 250, "Natural gas"));
		this.getRubyModels().add(createEnergyBuildingType("Gas Turbine Generator MK 4", 450, "Natural gas"));
		this.getRubyModels().add(createEnergyBuildingType("Gas Turbine Generator MK 5", 682, "Natural gas"));
		this.getRubyModels().add(createEnergyBuildingType("Gas Turbine Generator MK 6", 782, "Natural gas"));

		this.getRubyModels().add(createEnergyBuildingType("Coal Power Station MK 1", 800, "Coal"));
		this.getRubyModels().add(createEnergyBuildingType("Coal Power Station MK 2", 1600, "Coal"));
		this.getRubyModels().add(createEnergyBuildingType("Coal Power Station MK 3", 2000, "Coal"));
		this.getRubyModels().add(createEnergyBuildingType("Coal Power Station MK 4", 4000, "Coal"));
		this.getRubyModels().add(createEnergyBuildingType("Coal Power Station MK 5", 5600, "Coal"));

		this.getRubyModels().add(createEnergyBuildingType("Biomass Power Station MK 1", 100, "Biomass"));
		this.getRubyModels().add(createEnergyBuildingType("Biomass Power Station MK 2", 225, "Biomass"));
		this.getRubyModels().add(createEnergyBuildingType("Biomass Power Station MK 3", 450, "Biomass"));
		this.getRubyModels().add(createEnergyBuildingType("Biomass Power Station MK 4", 600, "Biomass"));
		this.getRubyModels().add(createEnergyBuildingType("Biomass Power Station MK 5", 760, "Biomass"));
		//  this.rubyModels.add(createEnergyBuildingType("Geothermal Power Station MK 1",))
	}

	private void fillEnergyBuildings()
	{
		this.getRubyModels().add(createEnergyBuilding("IjsselCentrale"));
		this.getRubyModels().add(createEnergyBuilding("MoerdijkCentrale"));
	}

	private EnergyBuilding createEnergyBuilding(String name)
	{
		Random rand = new Random();

		int next = rand.nextInt(this.energyBuildingTypes.size() - 1);

		EnergyBuilding result = this.getContext().createRubyModel(EnergyBuilding.class);
		result.setName(name);
		result.setBuildingType(this.energyBuildingTypes.get(next));

		try
		{
			result.Save();
		}
		catch (RubyException e)
		{
			e.printStackTrace();
		}

		return result;
	}

	private EnergyBuildingType createEnergyBuildingType(String name, int outputinMW, String energysource)
	{
		EnergyBuildingType result = this.getContext().createRubyModel(EnergyBuildingType.class);
		result.setPowerOutput(outputinMW);
		result.setName(name);
		result.setEnergySource(energysource);
		this.energyBuildingTypes.add(result);

		try
		{
			result.Save();
		}
		catch (RubyException e)
		{
			e.printStackTrace();
		}
		return result;
	}
}
