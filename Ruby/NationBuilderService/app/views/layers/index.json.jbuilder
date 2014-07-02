json.array!(@layers) do |layer|
  json.extract! layer, :id, :name
  json.url layer_url(layer, format: :json)
end
