package UnitTests;

import mocks.TestModel2;
import nationbuilder.lib.Ruby.services.ResolveUnresolvedFieldsService;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by patrick on 8/23/15.
 */
public class BulkSqlCreateServiceConnectorTest {


    @Test
    public void resolveUnresolvedFieldsTest() {
        TestModel2 rubyTestModel = new TestModel2();
        ResolveUnresolvedFieldsService service = new ResolveUnresolvedFieldsService();
        String actual = service.resolve(TestModel2.class,rubyTestModel,"187414,<bui>testmodel1_id<eui>");
        String expected  = "187414,12345";

        Assert.assertEquals(actual,expected);

    }
}
