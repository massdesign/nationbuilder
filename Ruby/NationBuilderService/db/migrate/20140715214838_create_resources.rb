class CreateResources < ActiveRecord::Migration
  def change
    create_table :resources do |t|
      t.timestamps
      #t.belongs_to :terraintype
	  t.belongs_to :tile
    end
  end
end
