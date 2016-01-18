require 'test_helper'

class PowerRelayStationsControllerTest < ActionController::TestCase
  setup do
    @power_relay_station = power_relay_stations(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:power_relay_stations)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create power_relay_station" do
    assert_difference('PowerRelayStation.count') do
      post :create, power_relay_station: { capacity: @power_relay_station.capacity }
    end

    assert_redirected_to power_relay_station_path(assigns(:power_relay_station))
  end

  test "should show power_relay_station" do
    get :show, id: @power_relay_station
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @power_relay_station
    assert_response :success
  end

  test "should update power_relay_station" do
    patch :update, id: @power_relay_station, power_relay_station: { capacity: @power_relay_station.capacity }
    assert_redirected_to power_relay_station_path(assigns(:power_relay_station))
  end

  test "should destroy power_relay_station" do
    assert_difference('PowerRelayStation.count', -1) do
      delete :destroy, id: @power_relay_station
    end

    assert_redirected_to power_relay_stations_path
  end
end
