class Tile < ActiveRecord::Base 
 belongs_to :layer
 has_one :image
 has_one :resource
 has_many :claims
 has_and_belongs_to_many :militarystrongholds
end
