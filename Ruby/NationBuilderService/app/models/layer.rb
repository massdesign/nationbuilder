class Layer < ActiveRecord::Base
has_many :tiles
belongs_to :map
end
