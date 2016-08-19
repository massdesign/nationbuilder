# encoding: UTF-8
# This file is auto-generated from the current state of the database. Instead
# of editing this file, please use the migrations feature of Active Record to
# incrementally modify your database, and then regenerate this schema definition.
#
# Note that this schema.rb definition is the authoritative source for your
# database schema. If you need to create the application database on another
# system, you should be using db:schema:load, not running all the migrations
# from scratch. The latter is a flawed and unsustainable approach (the more migrations
# you'll amass, the slower it'll run and the greater likelihood for issues).
#
# It's strongly recommended that you check this file into your version control system.

ActiveRecord::Schema.define(version: 20160120123638) do

  create_table "buildings", force: true do |t|
    t.string   "name"
    t.integer  "warehouse_id"
    t.integer  "power_relay_station_id"
    t.integer  "military_base_id"
    t.integer  "game_entity_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "cities", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "population"
  end

  create_table "claims", force: true do |t|
    t.integer  "tile_id"
    t.integer  "state_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "currencies", force: true do |t|
    t.string   "name"
    t.string   "status"
    t.string   "convertable"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "energy_building_types", force: true do |t|
    t.string   "energySource"
    t.integer  "powerOutput"
    t.string   "name"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "energy_buildings", force: true do |t|
    t.string   "name"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "energy_building_type_id"
    t.integer  "tile_id"
  end

  create_table "game_entities", force: true do |t|
    t.string   "name"
    t.integer  "city_id"
    t.integer  "node_type_id"
    t.integer  "militarystronghold_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "images", force: true do |t|
    t.integer  "map_id"
    t.string   "name"
    t.string   "url"
    t.integer  "width"
    t.integer  "height"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "layers", force: true do |t|
    t.integer  "map_id"
    t.string   "zindex"
    t.string   "name"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "maps", force: true do |t|
    t.integer  "width"
    t.integer  "height"
    t.integer  "tileWidth"
    t.integer  "tileHeight"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "militarystrongholds", force: true do |t|
    t.integer  "health"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "militarystrongholds_tiles", force: true do |t|
    t.integer "tile_id"
    t.integer "militarystronghold_id"
  end

  create_table "node_types", force: true do |t|
    t.string   "name"
    t.boolean  "destroyable"
    t.integer  "power_grid_node_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "power_connections", force: true do |t|
    t.integer  "load"
    t.integer  "capacity"
    t.string   "name"
    t.integer  "power_grid_node_a_id"
    t.integer  "power_grid_node_b_id"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "power_grid_nodes", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "power_relay_station_id"
  end

  create_table "power_relay_station_types", force: true do |t|
    t.string   "name"
    t.integer  "capacity"
    t.string   "responsetime"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "power_relay_stations", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "power_relay_station_type_id"
  end

  create_table "resources", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "tile_id"
    t.integer  "resourcetype_id"
  end

  create_table "resourcetypes", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "name"
    t.string   "location"
    t.boolean  "regenerating"
  end

  create_table "states", force: true do |t|
    t.string   "motto"
    t.string   "name"
    t.string   "currency_id"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.string   "user_id"
  end

  create_table "terraintypes", force: true do |t|
    t.string   "name"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "tiles", force: true do |t|
    t.integer  "gidtag"
    t.integer  "xposition"
    t.integer  "yposition"
    t.integer  "xoffset"
    t.integer  "yoffset"
    t.datetime "created_at"
    t.datetime "updated_at"
    t.integer  "layer_id"
    t.integer  "image_id"
    t.integer  "terraintype_id"
  end

  create_table "users", force: true do |t|
    t.string   "screenname"
    t.string   "loginname"
    t.string   "passwordhash"
    t.string   "registerdate"
    t.string   "emailadres"
    t.datetime "created_at"
    t.datetime "updated_at"
  end

  create_table "warehouses", force: true do |t|
    t.datetime "created_at"
    t.datetime "updated_at"
  end

end
