class CreatePowerRelayStationTypes < ActiveRecord::Migration
  def change
    create_table :power_relay_station_types do |t|
      t.string :name
      t.integer :capacity
      t.timestamps
    end
  end
end
