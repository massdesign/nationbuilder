package mocks;

import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.http.data.QueryManager;
import nationbuilder.lib.http.data.ResponseData;
import nationbuilder.lib.sql.TableMetaData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by patrick on 8/23/15.
 */
public class QueryManagerMock implements QueryManager
{
    @Override
    public int getCurrentID() throws SQLException {
        return 0;
    }

    @Override
    public int getNextID() throws SQLException {
        return 0;
    }

    @Override
    public void executeInsert(String sql) {

    }

    @Override
    public void executeUpdate(String sql) {

    }

    @Override
    public TableMetaData getTableStructure(String tableName) throws SQLException {
        return null;
    }

    @Override
    public HttpResponseData executeSelect(String sql) {
        return null;
    }

    @Override
    public ResponseData executeBulkInsert(List<String> rows, String tableName) throws SQLException {
        return null;
    }
}
