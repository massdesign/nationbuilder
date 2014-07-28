class CreateResourcesResourcetype < ActiveRecord::Migration
  def change
    create_table :resources_resourcetypes do |t|
    t.integer :resource_id 
    t.integer :resourcetype_id 
   end
  end
end
