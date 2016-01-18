class PowerGridNode < ActiveRecord::Base
has_one :node_type
has_one :power_relay_station
end
