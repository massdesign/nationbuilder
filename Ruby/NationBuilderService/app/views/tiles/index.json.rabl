class PrettyJson
def self.dump(object)
 JSON.pretty_generate(object, {:indent => " "})
end
end
Rabl.configure do |config|
 config.json_engine = PrettyJson
 config.include_json_root = false
end
object @tile
attributes :xposition,:yposition,:xoffset,:yoffset


collection @tiles
