package nationbuilder.lib.http.data;

/**
 * Created by patrick on 12/16/14.
 */
public class SqlResponseData implements ResponseData
{
    private int rowsAffected;
    private String sql;

    public int getRowsAffected() {

        return rowsAffected;
    }


    public void setRowsAffected(int rowsAffected) {
        this.rowsAffected = rowsAffected;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
