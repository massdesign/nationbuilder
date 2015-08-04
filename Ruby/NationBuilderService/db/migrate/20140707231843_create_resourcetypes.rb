class CreateResourcetypes < ActiveRecord::Migration
  def change
    create_table :resourcetypes do |t|

      t.timestamps
      t.string :name
      t.string :location
      t.boolean :regenerating
    end
  end
end
