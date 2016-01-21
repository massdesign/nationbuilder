package nationbuilder.lib.data.map.entities;

import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Association.annotation.Transient;
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
	@OneToOne(mapIdTo = "aid",foreignKey = "power_grid_node_a_id")
	private PowerGridNode A;

	/**
	 * Right hand side of the connection
	 */
	@OneToOne(mapIdTo = "bid",foreignKey = "power_grid_node_b_id")
	private PowerGridNode B;

    @Transient
	private String bid;
    @Transient
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
