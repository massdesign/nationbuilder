package nationbuilder.lib.sql;

/**
 * Created by patrick on 12/25/14.
 */
public class TableRow {

    private ColumnValue[] row;
    private  TableMetaData tableMetaData;
    public TableRow(int length,TableMetaData metaData)
    {
        this.row = new ColumnValue[length];
        this.tableMetaData = metaData;
    }
    public void setColumn(String columnName, Object value)
    {
        String resultValue = "";
        if(value instanceof  Integer)
        {
           resultValue =  String.valueOf((int)value);
        }
        else if(value instanceof String)
        {
            resultValue = (String)value;
        }

        for(int i=0;i<this.tableMetaData.getSortedColumns().size();i++)
        {
            if(this.tableMetaData.getSortedColumns().get(i).getColumnName().equals(columnName))
            {
                if(i <= row.length)
                {
                    this.row[i] = new ColumnValue(this.tableMetaData.getSortedColumns().get(i),resultValue);
                    break;
                }
                else
                {
                    // throw some nasty error with index out of range because of exception
                }
            }
        }
    }
}
