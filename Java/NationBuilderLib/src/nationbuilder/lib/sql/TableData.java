package nationbuilder.lib.sql;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 12/22/14.
 */
public class TableData
{
	private String table;
	private List<Column> sortedColumns;

	public List<Column> getSortedColumns()
	{
		return sortedColumns;
	}

	public TableData()
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


	public void addColumn(Column column) {
		this.sortedColumns.add(column);
	}

}
