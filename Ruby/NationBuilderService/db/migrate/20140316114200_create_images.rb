class CreateImages < ActiveRecord::Migration
  def change
    create_table :images do |t|
      t.belongs_to:map
      t.string :name
      t.string :url
      t.integer :width
      t.integer :height
      t.timestamps
    end
  end
end
