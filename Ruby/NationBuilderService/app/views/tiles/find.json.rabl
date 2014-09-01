Rabl.configure do |config|
 #config.include_json_root = false
 config.enable_json_callbacks = true
end
object @resource
child :tiles do
attributes :xposition,:yposition,:xoffset,:yoffset
end
child :resourcetype do
attributes :name,:location,:regenerating
end

	



