class Building < ActiveRecord::Base
has_one  :game_entity
belongs_to :tile
end
