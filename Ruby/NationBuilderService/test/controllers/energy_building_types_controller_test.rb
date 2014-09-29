require 'test_helper'

class EnergyBuildingTypesControllerTest < ActionController::TestCase
  setup do
    @energy_building_type = energy_building_types(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:energy_building_types)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create energy_building_type" do
    assert_difference('EnergyBuildingType.count') do
      post :create, energy_building_type: { energySource: @energy_building_type.energySource, name: @energy_building_type.name, powerOutput: @energy_building_type.powerOutput }
    end

    assert_redirected_to energy_building_type_path(assigns(:energy_building_type))
  end

  test "should show energy_building_type" do
    get :show, id: @energy_building_type
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @energy_building_type
    assert_response :success
  end

  test "should update energy_building_type" do
    patch :update, id: @energy_building_type, energy_building_type: { energySource: @energy_building_type.energySource, name: @energy_building_type.name, powerOutput: @energy_building_type.powerOutput }
    assert_redirected_to energy_building_type_path(assigns(:energy_building_type))
  end

  test "should destroy energy_building_type" do
    assert_difference('EnergyBuildingType.count', -1) do
      delete :destroy, id: @energy_building_type
    end

    assert_redirected_to energy_building_types_path
  end
end
