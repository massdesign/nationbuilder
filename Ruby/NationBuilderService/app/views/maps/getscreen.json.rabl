collection @maps
attributes :width, :height, :tileWidth,:tileHeight
child :layers do
	attributes :name,:zindex
		child :tiles do
		attributes :xposition,:yposition,:xoffset,:yoffset,:image_id
	end
 end
collection @maps





