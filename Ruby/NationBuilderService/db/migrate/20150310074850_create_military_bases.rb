class CreateMilitaryBases < ActiveRecord::Migration
  def change
    create_table :military_bases do |t|
      t.integer :health
      t.timestamps
    end
  end
end
