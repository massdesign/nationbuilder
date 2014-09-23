class EnergyBuilding < ActiveRecord::Base
  belongs_to :energy_building_type
  belongs_to :tile
end
