import mocks.TestModel1;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.sql.ObjectMap;
import nationbuilder.lib.sql.SqlObjectToRowConverter;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by patrick on 8/23/15.
 */
public class SqlObjectToRowConverterTest
{


    @Test
    public  void createObjectMapTest() throws MissingAnnotationException
    {

        SqlObjectToRowConverter sqlObjectToRowConverter = new SqlObjectToRowConverter();

        ObjectMap result =  sqlObjectToRowConverter.createObjectMap(new TestModel1());

        Assert.assertNotNull(result);
        Assert.assertEquals(result.getKvMap().size(),8);


        Assert.assertNotNull(result.getKvMap().get("a"));
        Assert.assertEquals(result.getKvMap().get("a"),"a");

        Assert.assertNotNull(result.getKvMap().get("b"));
        Assert.assertEquals(result.getKvMap().get("b"), "b");

        Assert.assertNotNull(result.getKvMap().get("c"));
        Assert.assertEquals(result.getKvMap().get("c"), "c");

        Assert.assertNotNull(result.getKvMap().get("d"));
        Assert.assertEquals(result.getKvMap().get("d"), "d");

        Assert.assertNotNull(result.getKvMap().get("e"));
        Assert.assertEquals(result.getKvMap().get("e"), 1);

        Assert.assertNotNull(result.getKvMap().get("f"));
        Assert.assertEquals(result.getKvMap().get("f"), 2);
    }
}
