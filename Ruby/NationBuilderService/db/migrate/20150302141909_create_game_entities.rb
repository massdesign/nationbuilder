class CreateGameEntities < ActiveRecord::Migration
  def change
    create_table :game_entities do |t|
      t.string :name
      t.integer :city_id
      t.timestamps
    end
  end
end
