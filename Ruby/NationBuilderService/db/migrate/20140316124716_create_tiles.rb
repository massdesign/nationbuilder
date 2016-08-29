class CreateTiles < ActiveRecord::Migration
  def change
    create_table :tiles do |t|
      t.integer :gidtag
      t.integer :xposition
      t.integer :yposition
      t.integer :xoffset
      t.integer :yoffset
      t.timestamps
      t.belongs_to :layer
      t.belongs_to :image 
      t.belongs_to :terraintype
      t.belongs_to :building  
     #t.belongs_to :resource
 	end
  end
end
