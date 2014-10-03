import nationbuilder.lib.Ruby.Generation.RubyScaffoldGenerator;
import nationbuilder.lib.data.map.entities.GameEntity;
import nationbuilder.lib.data.map.entities.Test;

/**
 * Created by patrick on 10/1/14.
 */
public class Generator
{

	public static void main(String[] args)
	{
		RubyScaffoldGenerator scaffoldGenerator = new RubyScaffoldGenerator();
		String scaffoldcmd = scaffoldGenerator.createScaffold(GameEntity.class);
		scaffoldGenerator.writeRablidFileToTemp(GameEntity.class);
		String cpcmd = scaffoldGenerator.createCpcommand(GameEntity.class);

		scaffoldGenerator.writeControllerToFile(Test.class);

		print(scaffoldcmd);
		print(cpcmd);
	}

	public static void print(String s)
	{
		System.out.println(s);
	}

}
