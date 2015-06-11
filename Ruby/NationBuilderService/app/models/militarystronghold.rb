class Militarystronghold < ActiveRecord::Base
has_one  :game_entity
has_and_belongs_to_many :tiles	
end
