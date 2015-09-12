import java.lang.reflect.Field;
import mocks.TestModel1;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author patrick.ekkel
 */
public class RubyAssociationResolverTest
{
	@Test
	public void getMappingInfoFindFieldTest() throws NoSuchFieldException
	{

		TestModel1 testModel1 = new TestModel1();
		Field field = testModel1.getClass().getDeclaredField("a");
		Field mappedField = RubyAssociationResolver.getMappedField(field, testModel1.getClass());

		Assert.assertNotNull(mappedField);

		String actual = mappedField.getName();
		String expected = "test12345";
		Assert.assertEquals(actual,expected);
	}
	@Test
	public void getMappingInfoCheckNullValue() throws NoSuchFieldException
	{
		TestModel1 testModel1 = new TestModel1();

		Field field = testModel1.getClass().getDeclaredField("b");
		Field mappedField = RubyAssociationResolver.getMappedField(field, testModel1.getClass());
		Assert.assertNull(mappedField);
	}
	@Test
	public void getMappingInfoInvalidMapping() throws NoSuchFieldException
	{

		TestModel1 testModel1 = new TestModel1();
		Field field = testModel1.getClass().getDeclaredField("d");

	 	Field mappedField = RubyAssociationResolver.getMappedField(field, testModel1.getClass());
		Assert.assertNull(mappedField);


	}


}
