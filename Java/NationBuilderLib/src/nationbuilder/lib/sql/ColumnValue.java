package nationbuilder.lib.sql;

/**
 * Created by patrick on 12/25/14.
 */
public class ColumnValue {

    public  ColumnValue(ColumnMetaData columnMetaData,String value)
    {


        this.columnMetaData = columnMetaData;
        this.value = value;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    private String value;

    private ColumnMetaData columnMetaData;


}
