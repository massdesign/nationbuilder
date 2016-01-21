package nationbuilder.lib.sql;

/**
 * Created by patrick on 12/23/14.
 */
public class ColumnMetaData
{
	private String columnName;
	private String columnType;


	public String getColumnType()
	{
		return columnType;
	}

	public void setColumnType(String columnType)
	{
		this.columnType = columnType;
	}
	public ColumnMetaData(String columnName, String columnType)
	{
		this.columnName = columnName;
		this.columnType = columnType;
	}
	public String getColumnName()
	{
		return columnName;
	}

	public void setColumnName(String columnName)
	{
		this.columnName = columnName;
	}
}
