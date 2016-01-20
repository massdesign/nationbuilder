require 'test_helper'

class PowerRelayStationTypesControllerTest < ActionController::TestCase
  setup do
    @power_relay_station_type = power_relay_station_types(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:power_relay_station_types)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create power_relay_station_type" do
    assert_difference('PowerRelayStationType.count') do
      post :create, power_relay_station_type: { capacity: @power_relay_station_type.capacity, name: @power_relay_station_type.name }
    end

    assert_redirected_to power_relay_station_type_path(assigns(:power_relay_station_type))
  end

  test "should show power_relay_station_type" do
    get :show, id: @power_relay_station_type
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @power_relay_station_type
    assert_response :success
  end

  test "should update power_relay_station_type" do
    patch :update, id: @power_relay_station_type, power_relay_station_type: { capacity: @power_relay_station_type.capacity, name: @power_relay_station_type.name }
    assert_redirected_to power_relay_station_type_path(assigns(:power_relay_station_type))
  end

  test "should destroy power_relay_station_type" do
    assert_difference('PowerRelayStationType.count', -1) do
      delete :destroy, id: @power_relay_station_type
    end

    assert_redirected_to power_relay_station_types_path
  end
end
