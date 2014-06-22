class CreateTiles < ActiveRecord::Migration
  def change
    create_table :tiles do |t|
      t.integer :xposition
      t.integer :yposition
      t.integer :xoffset
      t.integer :yoffset
      t.timestamps
      t.belongs_to :layer
      t.belongs_to :image 
 	end
  end
end
