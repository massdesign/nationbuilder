class CreateEnergyBuildings < ActiveRecord::Migration
  def change
    create_table :energy_buildings do |t|
      t.string :name
      t.timestamps
      t.belongs_to :energy_building_type
      t.belongs_to :tile
    end
  end
end