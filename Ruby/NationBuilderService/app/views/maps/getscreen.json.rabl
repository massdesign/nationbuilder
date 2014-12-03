class PrettyJson
def self.dump(object)
 JSON.pretty_generate(object, {:indent => " "})
end
end
Rabl.configure do |config|
 config.include_json_root = false
 #TODO: geen resource object teruggeven in het resource blok, dit geeft problemen met de RubyJava Interface 
 #config.enable_json_callbacks = true
 config.json_engine = PrettyJson
end

collection @maps
attributes :width, :height, :tileWidth,:tileHeight
child :layers do
	attributes :name,:zindex
		child :tiles do
		attributes :xposition,:yposition,:xoffset,:yoffset,:image_id,:id
	end
 end
collection @maps





