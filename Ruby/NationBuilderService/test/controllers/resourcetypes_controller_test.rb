require 'test_helper'

class ResourcetypesControllerTest < ActionController::TestCase
  setup do
    @resourcetype = resourcetypes(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:resourcetypes)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create resourcetype" do
    assert_difference('Resourcetype.count') do
      post :create, resourcetype: {  }
    end

    assert_redirected_to resourcetype_path(assigns(:resourcetype))
  end

  test "should show resourcetype" do
    get :show, id: @resourcetype
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @resourcetype
    assert_response :success
  end

  test "should update resourcetype" do
    patch :update, id: @resourcetype, resourcetype: {  }
    assert_redirected_to resourcetype_path(assigns(:resourcetype))
  end

  test "should destroy resourcetype" do
    assert_difference('Resourcetype.count', -1) do
      delete :destroy, id: @resourcetype
    end

    assert_redirected_to resourcetypes_path
  end
end
