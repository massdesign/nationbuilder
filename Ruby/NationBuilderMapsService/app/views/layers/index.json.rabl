Rabl.configure do |config|
 config.include_json_root = false
 config.enable_json_callbacks = true
end
@Layer
attributes :name
child :tiles do
attributes :xposition,:yposition,:xoffset,:yoffset
end
collection @layers