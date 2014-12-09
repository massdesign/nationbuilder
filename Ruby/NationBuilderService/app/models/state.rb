class State < ActiveRecord::Base
has_one :currency
has_many :claims
end
