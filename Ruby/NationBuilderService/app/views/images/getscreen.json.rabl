#collection @tiles
@tile
attributes :xposition,:yposition,:xoffset,:yoffset

attributes :name, :url, :id
child :tiles do
attributes :xposition,:yposition,:xoffset,:yoffset
end
collection @images
