class CreateCurrencies < ActiveRecord::Migration
  def change
    create_table :currencies do |t|
      t.string :name
      t.string :status
      t.string :convertable

      t.timestamps
    end
  end
end
