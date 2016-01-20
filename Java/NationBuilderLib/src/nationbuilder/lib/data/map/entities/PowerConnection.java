package nationbuilder.lib.data.map.entities;

import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_connections")
public class PowerConnection extends BaseRubyModel
{
	/**
	 * Name of the power line (most likely a generated value
	 */
	private String name;

	/**
	 * Max capacity that can be handled by this connection
	 */
	private int capacity;

	/**
	 * Current load that is being handled by this connection
	 */
	private int load;

	/**
	 * Left hand side of the connection
	 */
	@OneToOne(mapIdTo = "aid")
	private PowerGridNode A;

	/**
	 * Right hand side of the connection
	 */
	@OneToOne(mapIdTo = "bid")
	private PowerGridNode B;


	private String bid;
	private String aid;

	public PowerGridNode getA()
	{
		return A;
	}

	public void setA(PowerGridNode a)
	{
		A = a;
	}

	public PowerGridNode getB()
	{
		return B;
	}

	public void setB(PowerGridNode b)
	{
		B = b;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getCapacity()
	{
		return capacity;
	}

	public void setCapacity(int capacity)
	{
		this.capacity = capacity;
	}

	public int getLoad()
	{
		return load;
	}

	public void setLoad(int load)
	{
		this.load = load;
	}
}
