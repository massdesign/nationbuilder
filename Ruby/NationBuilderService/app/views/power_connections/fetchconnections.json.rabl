#config.include_json_root = true
# TODO: kijken of we DRY kunnen toepassen. 2 power_grid_nodes a en b tonen hetzelfde. eventueel met extends wat gaan doen hier
# Er wordt nog teveel getoond.. null waardes en dergelijke
object @power_connection
	child :power_grid_node_a => :power_grid_node_a do
	attributes :name
		child :power_relay_station do
			attributes :name	
			child :power_relay_station_type do
				attributes :name			
			end 
		end
		child :energy_building do			
			child :building do
				attributes :name, :capacity	
				child :tile do
					attributes :id				
				end			
			end	
			child :energy_building_type do
				attributes :name, :capacity, :powerOutput				
			end	
		end
		child :power_grid_node do
			attributes :id		
		end
	end

	child :power_grid_node_b => :power_grid_node_b do
		attributes :name
		child :power_relay_station do
			attributes :name	
			child :power_relay_station_type do
				attributes :name,:capacity			
			end 
		end
		child :energy_building do			
			child :building do
				attributes :name			
			end	
			child :energy_building_type do
				attributes :name,:powerOutput				
			end	
		end
		child :power_grid_node do
			attributes :id		
		end
	end
collection @power_connections

