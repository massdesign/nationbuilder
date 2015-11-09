package nationbuilder.lib.sql;

import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.data.map.ResponseData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by patrick on 8/23/15.
 */
public interface QueryManager {

    public int getCurrentID() throws SQLException;
    public int getNextID() throws SQLException;
    public void executeInsert(String sql);
    public void executeUpdate(String sql);
    public TableMetaData getTableStructure(String tableName) throws SQLException;
    public HttpResponseData executeSelect(String sql);
    public ResponseData executeBulkInsert(List<String> rows,String tableName) throws SQLException;
}
