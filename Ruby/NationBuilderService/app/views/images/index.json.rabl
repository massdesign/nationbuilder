Rabl.configure do |config|
 config.include_json_root = false
 config.enable_json_callbacks = true
end
object @image
attributes :name, :url, :id
child :tiles do
attributes :xposition,:yposition,:xoffset,:yoffset
end
collection @images
