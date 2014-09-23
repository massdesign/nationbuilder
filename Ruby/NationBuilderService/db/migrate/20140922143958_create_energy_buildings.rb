class CreateEnergyBuildings < ActiveRecord::Migration
  def change
    create_table :energy_buildings do |t|
      t.string :name
      t.integer :poweroutput
      t.string :type

      t.timestamps
    end
  end
end
