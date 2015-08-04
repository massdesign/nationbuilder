class CreateTerraintypes < ActiveRecord::Migration
  def change
    create_table :terraintypes do |t|
      t.string :name
      t.timestamps
    end
  end
end
