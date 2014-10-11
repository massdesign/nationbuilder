class CreateUsers < ActiveRecord::Migration
  def change
    create_table :users do |t|
      t.string :screenname
      t.string :loginname
      t.string :passwordhash
      t.string :registerdate
      t.string :emailadres
      t.timestamps
    end
  end
end
