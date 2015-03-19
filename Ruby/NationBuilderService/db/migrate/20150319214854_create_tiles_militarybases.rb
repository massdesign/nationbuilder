class CreateTilesMilitarybases < ActiveRecord::Migration
  def change
    create_table :tiles_militarybases do |t|
	 t.column 'tile_id', :integer
	 t.column 'military_base_id', :integer
    end
  end
end
