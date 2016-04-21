package nationbuilder.lib.sql;

import nationbuilder.lib.http.data.HttpResponseData;
import nationbuilder.lib.data.map.ResponseData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by patrick on 8/23/15.
 */
public interface QueryManager {

     int getCurrentID() throws SQLException;
     int getNextID() throws SQLException;
     void executeInsert(String sql);
     void executeUpdate(String sql);
     TableMetaData getTableStructure(String tableName) throws SQLException;
     HttpResponseData executeSelect(String sql);
     ResponseData executeBulkInsert(List<String> rows,String tableName) throws SQLException;
}
