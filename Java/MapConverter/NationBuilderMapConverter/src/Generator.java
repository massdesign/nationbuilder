import nationbuilder.lib.Ruby.Generation.RubyScaffoldGenerator;
import nationbuilder.lib.data.map.entities.Currency;
import nationbuilder.lib.data.map.entities.GameEntity;
import nationbuilder.lib.data.map.entities.State;
import nationbuilder.lib.data.map.entities.Test;

/**
 * Created by patrick on 10/1/14.
 */
public class Generator
{

	public static void main(String[] args)
	{
		RubyScaffoldGenerator scaffoldGenerator = new RubyScaffoldGenerator();
		String scaffoldcmd = scaffoldGenerator.createScaffold(Currency.class);
		scaffoldGenerator.writeRablidFileToTemp(Currency.class);
		String cpcmd = scaffoldGenerator.createCpcommand(Currency.class);
	//	scaffoldGenerator.writeControllerToFile(Test.class);
		print(scaffoldcmd);
		print(cpcmd);
	}

	public static void print(String s)
	{
		System.out.println(s);
	}

}
