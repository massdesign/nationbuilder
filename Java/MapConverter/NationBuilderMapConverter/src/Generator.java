import nationbuilder.lib.Ruby.Generation.RubyScaffoldGenerator;
import nationbuilder.lib.data.map.entities.*;

/**
 * Created by patrick on 10/1/14.
 */
public class Generator
{

	public static void main(String[] args)
	{
		RubyScaffoldGenerator scaffoldGenerator = new RubyScaffoldGenerator();
		String scaffoldcmd = scaffoldGenerator.createScaffold(Claim.class);
		scaffoldGenerator.writeRablidFileToTemp(Claim.class);
		String cpcmd = scaffoldGenerator.createCpcommand(Claim.class);
	//	scaffoldGenerator.writeControllerToFile(Test.class);
		print(scaffoldcmd);
		print(cpcmd);
	}

	public static void print(String s)
	{
		System.out.println(s);
	}

}
