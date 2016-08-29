class Building < ActiveRecord::Base
has_one  :game_entity
has_many :tiles

end
