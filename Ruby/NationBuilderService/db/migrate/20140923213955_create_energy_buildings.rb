class CreateEnergyBuildings < ActiveRecord::Migration
  def change
    create_table :energy_buildings do |t|
      t.timestamps
      t.belongs_to :energy_building_type
    end
  end
end
