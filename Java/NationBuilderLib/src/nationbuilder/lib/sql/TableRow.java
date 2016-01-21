package nationbuilder.lib.sql;

import nationbuilder.lib.Ruby.Exceptions.ColumnNotFoundException;

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
    public void setColumn(String columnName, Object value) throws ColumnNotFoundException {
        String resultValue = "";

        if(value != null && value instanceof  Integer)
        {
           resultValue =  String.valueOf((int)value);
        }
        else if(value != null &&  value instanceof String)
        {
            resultValue = (String)value;
        }


        ColumnValue columnValue = null;
        int columnValueIndex = -1;
        for(int i=0;i<this.tableMetaData.getSortedColumns().size();i++)
        {
            columnValueIndex = i;
            if(this.tableMetaData.getSortedColumns().get(i).getColumnName().equals(columnName))
            {
               // if(i <= row.length)
                //{
                   // this.row[i] = new ColumnValue(this.tableMetaData.getSortedColumns().get(i),resultValue);
                    columnValue = new ColumnValue(this.tableMetaData.getSortedColumns().get(i),resultValue);
                    break;
                //}
                //else
                //{
                    // throw some nasty error with index out of range because of exception
                //}
            }
        }
        if(columnValue != null) {

          this.row[columnValueIndex] = columnValue;
        }
        else {
            throw new ColumnNotFoundException("Column: " + columnName + " not found on " + this.tableMetaData.getTable());
        }
    }

    public String createInsertStatement()
    {
        // TODO: implement
        return "";
    }
    public String createSelectStatement()
    {
        // TODO: implement
        return "";
    }
    public String createBulkInsertStatement()
    {
        String result = "";
        for(ColumnValue value : this.row)
        {
          if(value != null) {
              result += value.getValue();
              result += ",";
          }
          else
          {
              result += "0";
              result += ",";
          }
        }
        return  result.substring(0,result.length()-1);
    }
    public String createUpdateStatement()
    {
        // TODO: implement
        return "";
    }
}
