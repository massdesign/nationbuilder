class GameEntity < ActiveRecord::Base
has_many :buildings
has_many :military_bases
end
