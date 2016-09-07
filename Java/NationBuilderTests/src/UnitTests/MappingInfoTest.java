package UnitTests;

import java.lang.reflect.Field;
import mocks.TestModel1;
import mocks.TestModel2;
import nationbuilder.lib.Ruby.Association.MappingInfo;
import nationbuilder.lib.Ruby.Association.MappingInfoType;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author patrick.ekkel
 */
public class MappingInfoTest
{
	private String EXPECTED_KEY = "95906711491823150357980019632961662677";
	@Test
	public void  testGetObjectKey() throws NoSuchFieldException
	{
			TestModel1 testModel1 = new TestModel1();
			Field field = TestModel1.class.getDeclaredField("testModel1");
			MappingInfo mappingInfo = new MappingInfo("testmodel1", TestModel2.class, testModel1, field, null);
			mappingInfo.setMappingInfoType(MappingInfoType.OneToOne);
			String actual = mappingInfo.getObjectKey();
			Assert.assertTrue(actual.equals(EXPECTED_KEY));
	}
	@Test
	public void  testGetObjectKeyCache() throws NoSuchFieldException
	{

		TestModel1 testModel1 = new TestModel1();
		Field field = TestModel1.class.getDeclaredField("testModel1");
		MappingInfo mappingInfo = new MappingInfo("testmodel1", TestModel2.class, testModel1, field, null);
		mappingInfo.setMappingInfoType(MappingInfoType.OneToOne);
		String actual = mappingInfo.getObjectKey();
		String expected  = mappingInfo.getObjectKey();
		Assert.assertTrue(actual.equals(expected));
		Assert.assertTrue(expected.equals(EXPECTED_KEY));
		Assert.assertTrue(actual.equals(EXPECTED_KEY));

	}

}
