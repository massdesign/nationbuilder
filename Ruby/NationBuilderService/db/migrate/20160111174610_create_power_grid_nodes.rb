class CreatePowerGridNodes < ActiveRecord::Migration
  def change
    create_table :power_grid_nodes do |t|
      t.timestamps
      t.integer :power_relay_station_id   
      t.integer :energy_building_id
    end
  end
end
