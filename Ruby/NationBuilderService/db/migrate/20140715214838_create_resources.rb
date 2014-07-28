class CreateResources < ActiveRecord::Migration
  def change
    create_table :resources do |t|
      t.timestamps
      t.belongs_to :terraintype
    end
  end
end
