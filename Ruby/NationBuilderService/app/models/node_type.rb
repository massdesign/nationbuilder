class NodeType < ActiveRecord::Base
belongs_to :power_grid_node
has_one :energy_building,  :foreign_key => "power_grid_node_id"
has_one :power_relay_station, :foreign_key => "power_grid_node_id"
end
