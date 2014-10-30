class CreateStates < ActiveRecord::Migration
  def change
    create_table :states do |t|
      t.string :motto
      t.string :name
      t.string :currency_id
      t.timestamps
      t.string :user_id
    end
  end
end
