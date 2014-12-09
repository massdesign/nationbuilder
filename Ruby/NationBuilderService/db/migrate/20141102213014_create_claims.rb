class CreateClaims < ActiveRecord::Migration
  def change
    create_table :claims do |t|
      t.integer :tile_id
      t.integer :state_id

      t.timestamps
    end
  end
end
