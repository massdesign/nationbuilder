package nationbuilder.lib.connectors;

import java.sql.SQLException;
import java.util.HashMap;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.ResponseData;
import nationbuilder.lib.http.data.SqlQueryManager;
import nationbuilder.lib.sql.TableData;
import nationbuilder.lib.sql.ObjectMap;
import nationbuilder.lib.sql.SqlObjectToRowConverter;

/**
 * Created by patrick on 12/15/14.
 */
public class SqlObjectBuilder implements ObjectBuilder
{
    SqlObjectToRowConverter sqlObjectToRowConverter;

    private HashMap<String,TableData> sortedTables;

    private SqlQueryManager queryManager;
    public SqlObjectBuilder(SqlQueryManager queryManager)
    {
        this.sqlObjectToRowConverter = new SqlObjectToRowConverter();
        this.sortedTables = new HashMap<>();
        this.queryManager = queryManager;
    }


    @Override
    public Object createObjectFromString(ResponseData data, Class<?> clazz) throws ObjectConversionFailedException {
        return null;
    }

    @Override
    public String createStringFromObject(Object object) throws ObjectConversionFailedException
    {
        RubyModel model = (RubyModel)object;
        ObjectMap objectMap = sqlObjectToRowConverter.createObjectMap(model);


        Entity annotation =  model.getClass().getAnnotation(Entity.class);

        if(annotation != null)
        {
           String tableName =    annotation.tableName();

            try
            {
                this.queryManager.getTableStructure(tableName);
            }
            catch (SQLException e)
            {
                throw new ObjectConversionFailedException("Object conversion failed for" + object.getClass().getSimpleName() + " because of " + e.getMessage());
            }


        }
        else
        {
            // TODO: throw some error about annotation missing on the class
        }
        return "";
    }
}
