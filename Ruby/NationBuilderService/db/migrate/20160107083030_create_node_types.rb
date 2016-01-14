class CreateNodeTypes < ActiveRecord::Migration
  def change
    create_table :node_types do |t|
      t.string :name
      t.boolean :destroyable
      t.integer :power_grid_node_id
      t.timestamps
    end
  end
end
