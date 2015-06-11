class Warehouse < ActiveRecord::Base
has_one :building
belongs_to :buildings
end
