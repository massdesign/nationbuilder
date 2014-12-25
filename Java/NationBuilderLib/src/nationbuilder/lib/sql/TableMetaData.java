package nationbuilder.lib.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by patrick on 12/22/14.
 */
public class TableMetaData
{
	private String table;

    public List<ColumnMetaData> getSortedColumns() {
        return sortedColumns;
    }

    public void setSortedColumns(List<ColumnMetaData> sortedColumns) {
        this.sortedColumns = sortedColumns;
    }

    private List<ColumnMetaData> sortedColumns;

	public TableMetaData()
	{
		this.sortedColumns = new ArrayList<>();
	}

	public String getTable()
	{
		return table;
	}

	public void setTable(String table)
	{
		this.table = table;
	}

    public TableRow createnewRow()
    {
        return new TableRow(sortedColumns.size(),this);
    }



	public void addColumn(ColumnMetaData column) {
		this.sortedColumns.add(column);
	}

}
