class PowerRelayStation < ActiveRecord::Base
has_one :power_grid_node
has_one :building
belongs_to :power_grid_nodes
end
