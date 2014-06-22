json.array!(@maps) do |map|
  json.extract! map, :id, :width, :height, :tileWidth, :tileHeight
  json.url map_url(map, format: :json)
end
