class PowerRelayStation < ActiveRecord::Base
belongs_to :power_relay_station_type
has_one :power_grid_node
has_one :building
belongs_to :power_grid_nodes
end
