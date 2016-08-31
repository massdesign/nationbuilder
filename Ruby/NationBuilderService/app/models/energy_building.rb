class EnergyBuilding < ActiveRecord::Base
  belongs_to :energy_building_type
  belongs_to :tile
  has_one :building
end
