Rabl.configure do |config|
 config.include_json_root = false
 config.enable_json_callbacks = true
end
object @map

attributes :width, :height, :tileWidth,:tileHeight
