class PowerConnection < ActiveRecord::Base

#has_one :power_grid_node_a, :class_name => "PowerGridNode", :foreign_key => "power_grid_node_a_id"
#has_one :power_grid_node_b, :class_name => "PowerGridNode", :foreign_key => "power_grid_node_b_id"
belongs_to :power_grid_node_b, :class_name => "NodeType"
belongs_to :power_grid_node_a, :class_name => "NodeType"

end
