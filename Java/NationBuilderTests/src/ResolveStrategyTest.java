import java.lang.reflect.Field;
import mocks.TestModel3;
import nationbuilder.lib.Ruby.OneToManyStrategy;
import org.junit.Assert;
import org.junit.Test;
import mocks.TestModel1;
import mocks.TestModel2;
import nationbuilder.lib.Ruby.Association.MappingInfo;
import nationbuilder.lib.Ruby.resolvestrategies.OneToOneStrategy;
import nationbuilder.lib.Ruby.orm.ID;

/**
 * @author patrick.ekkel
 */
public class ResolveStrategyTest
{

	@Test
	public void oneToOneStrategyTest() throws NoSuchFieldException, IllegalAccessException
	{

		TestModel1 testModel1 = new TestModel1();
		ID expected_id = new ID();
		String expected = "3232";
		expected_id.setId(expected);
		testModel1.setId(expected_id);
		TestModel2 testModel2 = new TestModel2();
	    Field field =	testModel1.getClass().getDeclaredField("testModel1");
		field.setAccessible(true);
		testModel1.setTestModel1(testModel2);

		MappingInfo mappingInfo = new MappingInfo("testmodel1", TestModel2.class,testModel1,field,null);

		OneToOneStrategy oneToOneStrategy = new OneToOneStrategy("OneToOne Resolving Strategy");
		oneToOneStrategy.setMappingInfo(mappingInfo);
		oneToOneStrategy.setObjectToReference(testModel1);
		oneToOneStrategy.resolve();

		ID actual_id =   testModel2.getTestmodel1().getID();
		Assert.assertEquals(actual_id,expected_id);
	}
	@Test
	public void oneToManyStrategyTest() throws NoSuchFieldException, IllegalAccessException
	{

		TestModel3 testModel3 = new TestModel3();
		ID expected_id = new ID();
		String expected = "3232";
		expected_id.setId(expected);
		expected_id.setId(expected);
		testModel3.setId(expected_id);
		Field field = testModel3.getClass().getDeclaredField("testmodels");
		field.setAccessible(true);
		OneToManyStrategy oneToManyStrategy = new OneToManyStrategy("OneToMany Resolving Strategy");

		MappingInfo mappingInfo = new MappingInfo("testmodel1", TestModel2.class, testModel3, field,null);
		oneToManyStrategy.setMappingInfo(mappingInfo);
		oneToManyStrategy.setObjectToReference(testModel3);
		oneToManyStrategy.resolve();

		Assert.assertEquals(testModel3.getTestmodels().get(0).getTestmodel1().getID(),expected_id);
		Assert.assertEquals(testModel3.getTestmodels().get(1).getTestmodel1().getID(),expected_id);
		Assert.assertEquals(testModel3.getTestmodels().get(2).getTestmodel1().getID(),expected_id);


	}
}
