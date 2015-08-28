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

       // connector.resolveUnresolvedFields();

        Assert.fail();

    }

}
