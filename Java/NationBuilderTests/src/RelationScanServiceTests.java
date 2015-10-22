import java.util.HashMap;
import java.util.List;
import mocks.TestModel1;
import mocks.TestModel2;
import nationbuilder.lib.Ruby.Association.annotation.MappingInfo;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RelationScanService;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author patrick.ekkel
 */
public class RelationScanServiceTests
{
	@Test
	public void scanForRelationsTest() {

		HashMap<RubyModel,String> objects = new HashMap<>();
		TestModel1 testModel1 = new TestModel1();
		TestModel2 testModel2 = new TestModel2();
		String sqlString = "a,b,c,d,1,2";
		RelationScanService scanService = new RelationScanService();

		objects.put(testModel1,sqlString);

		List<MappingInfo> mappingInfos = scanService.scanForRelations(objects.entrySet().iterator());
		int expectedSize = 3;
		Assert.assertEquals(mappingInfos.size(),expectedSize);
		Assert.assertSame(testModel1,mappingInfos.get(0).getInstance());
		//Assert.assertEquals();

		//mappingInfos.get(0).
		/*for(MappingInfo mappingInfo : mappingInfos) {
			// TODO: implement asserts
			//if(mappingInfo.ge)
		}*/

	}

}
