object @militarystronghold
attributes :health
		child :game_entity do
			attributes :name
		end
		child :tiles do
			attributes :id,:xposition,:yposition,:xoffset,:yoffset
		end

