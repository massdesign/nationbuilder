
object @power_connection
	attributes :id
	child :power_grid_node_a do
		attributes :id		
	end
	child :power_grid_node_b do
		attributes :id, :name		
	end
	 	

	
collection @power_connections

