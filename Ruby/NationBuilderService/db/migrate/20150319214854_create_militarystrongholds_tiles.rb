class CreateMilitarystrongholdsTiles < ActiveRecord::Migration
  def change
    create_table :militarystrongholds_tiles do |t|
	 t.column 'tile_id', :integer
	 t.column 'militarystronghold_id', :integer
    end
  end
end
