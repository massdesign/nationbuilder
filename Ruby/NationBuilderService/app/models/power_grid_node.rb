class PowerGridNode < ActiveRecord::Base
has_one :node_type
has_one :power_relay_station
has_one :power_grid_node_a, :class_name => "PowerGridNode", :foreign_key => "power_grid_node_a_id"
has_one :power_grid_node_b, :class_name => "PowerGridNode", :foreign_key => "power_grid_node_b_id"
end
