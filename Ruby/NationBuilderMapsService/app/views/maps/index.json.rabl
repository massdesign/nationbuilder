Rabl.configure do |config|
 config.include_json_root = false
 config.enable_json_callbacks = true
end
collection @maps
attributes :width, :height, :tileWidth,:tileHeight
child :layers do
	attributes :name,:zindex
		child :tiles do
		attributes :xposition,:yposition,:xoffset,:yoffset,:image_id
		#child :image do
		#attributes :id
		#end
	end
 end
collection @maps

