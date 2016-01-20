class CreatePowerRelayStations < ActiveRecord::Migration
  def change
    create_table :power_relay_stations do |t|
      t.timestamps
      t.integer :power_relay_station_type_id 
    end
  end
end
