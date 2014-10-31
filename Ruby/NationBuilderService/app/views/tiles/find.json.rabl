Rabl.configure do |config|
 #config.include_json_root = false
 #TODO: geen resource object teruggeven in het resource blok, dit geeft problemen met de RubyJava Interface 
 config.enable_json_callbacks = true
end
object @resource
child :tiles do
attributes :xposition,:yposition,:xoffset,:yoffset
end
child :resourcetype do
attributes :name,:location,:regenerating
end

	



