class CreateLayers < ActiveRecord::Migration
  def change
    create_table :layers do |t|
      t.belongs_to:map
      t.string :zindex
      t.string :name
      t.timestamps
    end
  end
end
