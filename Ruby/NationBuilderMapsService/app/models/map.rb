class Map < ActiveRecord::Base
has_many :layers
has_many :images
end
