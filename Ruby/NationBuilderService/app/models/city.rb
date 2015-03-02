class City < ActiveRecord::Base
has_one  :game_entity
#belongs_to :gameentities
end

