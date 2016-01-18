class CreatePowerRelayStations < ActiveRecord::Migration
  def change
    create_table :power_relay_stations do |t|
      t.integer :capacity
      t.timestamps
    end
  end
end
