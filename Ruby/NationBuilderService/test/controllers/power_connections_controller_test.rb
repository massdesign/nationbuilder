require 'test_helper'

class PowerConnectionsControllerTest < ActionController::TestCase
  setup do
    @power_connection = power_connections(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:power_connections)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create power_connection" do
    assert_difference('PowerConnection.count') do
      post :create, power_connection: { capacity: @power_connection.capacity, load: @power_connection.load, name: @power_connection.name }
    end

    assert_redirected_to power_connection_path(assigns(:power_connection))
  end

  test "should show power_connection" do
    get :show, id: @power_connection
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @power_connection
    assert_response :success
  end

  test "should update power_connection" do
    patch :update, id: @power_connection, power_connection: { capacity: @power_connection.capacity, load: @power_connection.load, name: @power_connection.name }
    assert_redirected_to power_connection_path(assigns(:power_connection))
  end

  test "should destroy power_connection" do
    assert_difference('PowerConnection.count', -1) do
      delete :destroy, id: @power_connection
    end

    assert_redirected_to power_connections_path
  end
end
