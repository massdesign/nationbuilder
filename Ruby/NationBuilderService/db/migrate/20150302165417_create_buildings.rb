class CreateBuildings < ActiveRecord::Migration
  def change
    create_table :buildings do |t|
      t.string :name
      t.integer :warehouse_id
      t.integer :game_entity_id
      t.timestamps
    end
  end
end
