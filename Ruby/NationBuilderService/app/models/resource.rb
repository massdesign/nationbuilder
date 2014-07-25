class Resource < ActiveRecord::Base
belongs_to :terraintype
has_and_belongs_to_many :resourcetype	
has_many :tiles
end
