import mocks.QueryManagerMock;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.ID;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.http.data.SqlResponseData;
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
        ID result = (ID)sqlObjectBuilder.createObjectFromString(responseData,ID.class);

        String expected = "96037";
        String actual = result.getId();
        Assert.assertEquals(expected,actual);
    }
    @Test
    public void createStringFromObject() throws ObjectConversionFailedException, MissingAnnotationException {

        SqlObjectBuilder sqlObjectBuilder = new SqlObjectBuilder(new QueryManagerMock());

        ID objectToConvert = new ID();
        objectToConvert.setId("98012");

       String result =  sqlObjectBuilder.createStringFromObject(null);
    }
}
