class PowerConnection < ActiveRecord::Base
belongs_to :power_grid_node_b, :class_name => "PowerGridNode"
belongs_to :power_grid_node_a, :class_name => "PowerGridNode"
end
