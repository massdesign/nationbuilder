class CreateResourcetypes < ActiveRecord::Migration
  def change
    create_table :resourcetypes do |t|

      t.timestamps
    end
  end
end
