class CreateMaps < ActiveRecord::Migration
  def change
    create_table :maps do |t|
      t.integer :width
      t.integer :height
      t.integer :tileWidth
      t.integer :tileHeight

      t.timestamps
    end
  end
end
