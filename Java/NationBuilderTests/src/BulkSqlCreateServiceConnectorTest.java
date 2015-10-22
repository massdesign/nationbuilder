import mocks.TestModel1;
import mocks.TestModel2;
import nationbuilder.lib.sql.connectors.BulkSqlCreateServiceConnector;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by patrick on 8/23/15.
 */
public class BulkSqlCreateServiceConnectorTest {


    @Test
    public void resolveUnresolvedFieldsTest() {

        BulkSqlCreateServiceConnector connector = new BulkSqlCreateServiceConnector(null);

        TestModel2 rubyTestModel = new TestModel2();

        connector.resolveUnresolvedFields(rubyTestModel,"187414,<bui>testmodel1_id<eui>,186627");

        Assert.fail();

    }

}
