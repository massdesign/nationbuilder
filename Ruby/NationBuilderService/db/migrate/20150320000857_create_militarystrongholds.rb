class CreateMilitarystrongholds < ActiveRecord::Migration
  def change
    create_table :militarystrongholds do |t|
      t.integer :health
      t.timestamps
    end
  end
end
