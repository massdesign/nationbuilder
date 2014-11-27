class PrettyJson
def self.dump(object)
 JSON.pretty_generate(object, {:indent => " "})
end
end
Rabl.configure do |config|
 #config.include_json_root = false
 #TODO: geen resource object teruggeven in het resource blok, dit geeft problemen met de RubyJava Interface 
 config.enable_json_callbacks = true
 config.json_engine = PrettyJson
end


		
object @resource
child :tiles do
attributes :id,:xposition,:yposition,:xoffset,:yoffset
child :claims do
	child :state do
		attributes :id,:name,:motto
	end
	end
end


child :resourcetype do
attributes :name,:location,:regenerating
end




	



