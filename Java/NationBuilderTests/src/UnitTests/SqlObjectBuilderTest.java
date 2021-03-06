package UnitTests;

import mocks.QueryManagerMock;
import mocks.TestModel1;
import nationbuilder.lib.Ruby.Exceptions.ColumnNotFoundException;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.orm.ID;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.sql.SqlResponseData;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by patrick on 8/23/15.
 */
public class SqlObjectBuilderTest {

    @Test
    public void createObjectFromStringTest() throws ObjectConversionFailedException {

        SqlResponseData responseData = new SqlResponseData();
        responseData.setSql("96037,Dollar,0,0,0,0");

        SqlObjectBuilder sqlObjectBuilder = new SqlObjectBuilder(new QueryManagerMock());
        ID result = sqlObjectBuilder.createIDFromResponse(responseData);

        String expected = "96037";
        String actual = result.getId();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void createStringFromObject() throws ObjectConversionFailedException, MissingAnnotationException, ColumnNotFoundException {

        SqlObjectBuilder sqlObjectBuilder = new SqlObjectBuilder(new QueryManagerMock());

        String result =  sqlObjectBuilder.createStringFromObject(TestModel1.class,new TestModel1());

        String expected = "0,a,b,c,d,1,2,test12345";
        String actual = result;
        Assert.assertEquals(expected,actual);

    }
}
