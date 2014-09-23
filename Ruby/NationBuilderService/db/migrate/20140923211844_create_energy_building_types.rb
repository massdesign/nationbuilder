class CreateEnergyBuildingTypes < ActiveRecord::Migration
  def change
    create_table :energy_building_types do |t|
      t.string :energySource
      t.integer :powerOutput
      t.string :name
      t.timestamps
    end
  end
end
