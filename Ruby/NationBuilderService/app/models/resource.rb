class Resource < ActiveRecord::Base
has_one :terraintype
has_one :resourcetype	
has_many :tiles
end
