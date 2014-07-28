require 'test_helper'

class TerraintypesControllerTest < ActionController::TestCase
  setup do
    @terraintype = terraintypes(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:terraintypes)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create terraintype" do
    assert_difference('Terraintype.count') do
      post :create, terraintype: { name: @terraintype.name }
    end

    assert_redirected_to terraintype_path(assigns(:terraintype))
  end

  test "should show terraintype" do
    get :show, id: @terraintype
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @terraintype
    assert_response :success
  end

  test "should update terraintype" do
    patch :update, id: @terraintype, terraintype: { name: @terraintype.name }
    assert_redirected_to terraintype_path(assigns(:terraintype))
  end

  test "should destroy terraintype" do
    assert_difference('Terraintype.count', -1) do
      delete :destroy, id: @terraintype
    end

    assert_redirected_to terraintypes_path
  end
end
