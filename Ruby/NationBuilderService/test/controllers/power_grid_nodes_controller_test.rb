require 'test_helper'

class PowerGridNodesControllerTest < ActionController::TestCase
  setup do
    @power_grid_node = power_grid_nodes(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:power_grid_nodes)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create power_grid_node" do
    assert_difference('PowerGridNode.count') do
      post :create, power_grid_node: {  }
    end

    assert_redirected_to power_grid_node_path(assigns(:power_grid_node))
  end

  test "should show power_grid_node" do
    get :show, id: @power_grid_node
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @power_grid_node
    assert_response :success
  end

  test "should update power_grid_node" do
    patch :update, id: @power_grid_node, power_grid_node: {  }
    assert_redirected_to power_grid_node_path(assigns(:power_grid_node))
  end

  test "should destroy power_grid_node" do
    assert_difference('PowerGridNode.count', -1) do
      delete :destroy, id: @power_grid_node
    end

    assert_redirected_to power_grid_nodes_path
  end
end
