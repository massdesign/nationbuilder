class Claim < ActiveRecord::Base
has_one :tile
has_one :state
has_many :claims
end
