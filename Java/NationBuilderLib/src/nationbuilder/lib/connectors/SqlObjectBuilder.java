package nationbuilder.lib.connectors;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Exceptions.MissingAnnotationException;
import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.ID;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.http.data.QueryManager;
import nationbuilder.lib.http.data.ResponseData;
import nationbuilder.lib.http.data.SqlQueryManager;
import nationbuilder.lib.http.data.SqlResponseData;
import nationbuilder.lib.sql.TableMetaData;
import nationbuilder.lib.sql.ObjectMap;
import nationbuilder.lib.sql.SqlObjectToRowConverter;
import nationbuilder.lib.sql.TableRow;

/**
 * Created by patrick on 12/15/14.
 */
public class SqlObjectBuilder implements ObjectBuilder
{
    SqlObjectToRowConverter sqlObjectToRowConverter;

    private HashMap<String,TableMetaData> sortedTables;

    private QueryManager queryManager;
    public SqlObjectBuilder(QueryManager queryManager)
    {
        this.sqlObjectToRowConverter = new SqlObjectToRowConverter();
        this.sortedTables = new HashMap<>();
        this.queryManager = queryManager;
    }


    @Override
    public Object createObjectFromString(ResponseData data, Class<?> clazz) throws ObjectConversionFailedException {

        Object result = null;
        // make exception for ID type
        if(clazz.getSimpleName().equals("ID"))
        {

            if(data instanceof SqlResponseData)
            {
               String sqlString =   ((SqlResponseData) data).getSql();

               String id =  sqlString.split(",")[0];

                 result = new ID();
                 ((ID)result).setId(id);
            }
            else
            {
                // TODO: throw nasty exception that SqlResponseData is the only way
            }
        }
        return result;
    }

    @Override
    public String createStringFromObject(Object object) throws ObjectConversionFailedException, MissingAnnotationException {
        RubyModel model = (RubyModel)object;


        ObjectMap objectMap = sqlObjectToRowConverter.createObjectMap(model);
        String result = "";

        Entity annotation =  model.getClass().getAnnotation(Entity.class);

        if(annotation != null)
        {
           String tableName =    annotation.tableName();

            if(tableName != null && !tableName.equals(""))
            {

                try
                {
                    TableMetaData currentTable = null;
                    if (this.sortedTables.containsKey(tableName))
                    {
                        currentTable = this.sortedTables.get(tableName);
                    }
                    else
                    {
                        currentTable = this.queryManager.getTableStructure(tableName);
                        this.sortedTables.put(tableName, currentTable);
                    }

                    TableRow newRow = currentTable.createnewRow();

                    Iterator omi = objectMap.getKvMap().entrySet().iterator();

                    while (omi.hasNext())
                    {
                        Map.Entry pair = (Map.Entry) omi.next();
                        String key = (String) pair.getKey();
                        Object value;
                        if (key.equals("id"))
                        {
                            value = this.queryManager.getNextID();
                        }
                        else
                        {
                            value = pair.getValue();
                        }
                        newRow.setColumn(key, value);
                    }
                    return newRow.createBulkInsertStatement();
                }
                catch (SQLException e)
                {
                    throw new ObjectConversionFailedException(
                     "Object conversion failed for " + object.getClass().getSimpleName() + " because of " + e
                      .getMessage());
                }
            }
            else
            {
                throw new MissingAnnotationException("Tablename is empty on  " + object.getClass().getSimpleName());
            }


        }
        else
        {
            throw new MissingAnnotationException("Missing Entity annotation on " + object.getClass().getSimpleName());
        }
    }
}
