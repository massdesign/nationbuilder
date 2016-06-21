package mocks;

import java.lang.reflect.Field;
import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.sql.QueryManager;
import nationbuilder.lib.data.map.ResponseData;
import nationbuilder.lib.sql.ColumnMetaData;
import nationbuilder.lib.sql.TableMetaData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by patrick on 8/23/15.
 */
public class QueryManagerMock implements QueryManager
{
    private int currentId;
    // mocktable structure gebruiken of die samenstellen van het daadwerkelijke object
    private boolean useObjectTableStructure = false;

    public QueryManagerMock() {
        this.currentId = 0;
    }

    @Override
    public int getCurrentID() throws SQLException {
        return 0;
    }

    @Override
    public int getNextID() throws SQLException {
        this.currentId++;

        return this.currentId;
    }

    @Override
    public void executeInsert(String sql) {

    }

    @Override
    public void executeUpdate(String sql) {

    }

    @Override
    public TableMetaData getTableStructure(String tableName) throws SQLException {
        TableMetaData tableMetaData = new TableMetaData();
        if(!useObjectTableStructure)
        {

            tableMetaData.addColumn(new ColumnMetaData("id", "int(11)"));
            tableMetaData.addColumn(new ColumnMetaData("a", "varchar(11)"));
            tableMetaData.addColumn(new ColumnMetaData("b", "varchar(11)"));
            tableMetaData.addColumn(new ColumnMetaData("c", "varchar(11)"));
            tableMetaData.addColumn(new ColumnMetaData("d", "varchar(11)"));

            tableMetaData.addColumn(new ColumnMetaData("e", "int(11)"));
            tableMetaData.addColumn(new ColumnMetaData("f", "int(11)"));
            tableMetaData.addColumn(new ColumnMetaData("test12345", "varchar(30)"));
        }
        else {

            try
            {
               Class clazz = Class.forName(tableName);

               Field[] fields =  clazz.getDeclaredFields();

                tableMetaData.addColumn(new ColumnMetaData("id", "int(11)"));
                for(Field f : fields) {

                    if(f.getType().getSimpleName().toLowerCase().equals("string")) {

                        tableMetaData.addColumn(new ColumnMetaData(f.getName(),"varchar(30)"));
                    }
                    else if(f.getType().getSimpleName().toLowerCase().equals("int")) {
                        tableMetaData.addColumn(new ColumnMetaData(f.getName(),"int(11)"));
                    }
                }


            }
            catch (ClassNotFoundException e)
            {
                e.printStackTrace();
            }


        }

        return tableMetaData;

    }

    @Override
    public HttpResponseData executeSelect(String sql) {
        return null;
    }

    @Override
    public ResponseData executeBulkInsert(List<String> rows, String tableName) throws SQLException {
        return null;
    }

    public boolean isUseObjectTableStructure()
    {
        return useObjectTableStructure;
    }

    public void setUseObjectTableStructure(boolean useObjectTableStructure)
    {
        this.useObjectTableStructure = useObjectTableStructure;
    }
}
