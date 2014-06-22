class Tile < ActiveRecord::Base 
 belongs_to :layer
 has_one :image

end
