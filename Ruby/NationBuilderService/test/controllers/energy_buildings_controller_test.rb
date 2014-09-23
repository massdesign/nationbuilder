require 'test_helper'

class EnergyBuildingsControllerTest < ActionController::TestCase
  setup do
    @energy_building = energy_buildings(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:energy_buildings)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create energy_building" do
    assert_difference('EnergyBuilding.count') do
      post :create, energy_building: { name: @energy_building.name, poweroutput: @energy_building.poweroutput, type: @energy_building.type }
    end

    assert_redirected_to energy_building_path(assigns(:energy_building))
  end

  test "should show energy_building" do
    get :show, id: @energy_building
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @energy_building
    assert_response :success
  end

  test "should update energy_building" do
    patch :update, id: @energy_building, energy_building: { name: @energy_building.name, poweroutput: @energy_building.poweroutput, type: @energy_building.type }
    assert_redirected_to energy_building_path(assigns(:energy_building))
  end

  test "should destroy energy_building" do
    assert_difference('EnergyBuilding.count', -1) do
      delete :destroy, id: @energy_building
    end

    assert_redirected_to energy_buildings_path
  end
end
