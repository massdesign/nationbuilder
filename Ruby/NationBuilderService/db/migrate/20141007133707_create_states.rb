class CreateStates < ActiveRecord::Migration
  def change
    create_table :states do |t|
      t.string :motto
      t.string :name

      t.timestamps
    end
  end
end
