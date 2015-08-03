class Resource < ActiveRecord::Base
belongs_to :tile
has_one :resourcetype
end
