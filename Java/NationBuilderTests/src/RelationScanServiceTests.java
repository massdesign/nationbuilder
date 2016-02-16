import java.util.HashMap;
import java.util.List;
import mocks.TestModel1;
import mocks.TestModel2;
import nationbuilder.lib.Ruby.Association.annotation.MappingInfo;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RelationScanService;
import nationbuilder.lib.Ruby.orm.RubyObjectKey;
import org.junit.Assert;
import org.junit.Test;
/**
 * @author patrick.ekkel
 */
public class RelationScanServiceTests
{
	@Test
	public void scanForRelationsTest() {

		HashMap<RubyObjectKey, String> objects  = new HashMap<>();
		TestModel1 testModel1 = new TestModel1();
		String sqlString = "a,b,c,d,1,2";
		RelationScanService scanService = new RelationScanService();
		RubyObjectKey objectKey = new RubyObjectKey("testmodel",testModel1);
		objects.put(objectKey,sqlString);

		List<MappingInfo> mappingInfos = scanService.scanForRelations(objects.entrySet().iterator());
		int expectedSize = 3;
		Assert.assertEquals(mappingInfos.size(),expectedSize);
		Assert.assertSame(testModel1,mappingInfos.get(0).getInstance());
	}

}
