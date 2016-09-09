class CreateEnergyBuildings < ActiveRecord::Migration
  def change
    create_table :energy_buildings do |t|
      t.timestamps
      t.belongs_to :energy_building_type
      t.integer :power_grid_node_id  
   end
  end
end
