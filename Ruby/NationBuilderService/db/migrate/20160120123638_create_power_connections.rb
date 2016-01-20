class CreatePowerConnections < ActiveRecord::Migration
  def change
    create_table :power_connections do |t|
      t.integer :load
      t.integer :capacity
      t.string :name
      t.integer :power_grid_node_a_id
      t.integer :power_grid_node_b_id
      t.timestamps
    end
  end
end
