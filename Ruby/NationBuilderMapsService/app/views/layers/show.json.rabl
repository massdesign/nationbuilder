Rabl.configure do |config|
 config.include_json_root = false
 config.enable_json_callbacks = true
end
object @layer
attributes :name
#child :tiles do
 #attributes :xposition, :yposition, :xoffset, :yoffset
 #	child :image do 
 #	attributes :name, :url
 #	end
#end
