class CreatePowerGridNodes < ActiveRecord::Migration
  def change
    create_table :power_grid_nodes do |t|
      t.timestamps
    end
  end
end
