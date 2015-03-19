require 'test_helper'

class MilitaryBasesControllerTest < ActionController::TestCase
  setup do
    @military_base = military_bases(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:military_bases)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create military_base" do
    assert_difference('MilitaryBase.count') do
      post :create, military_base: {  }
    end

    assert_redirected_to military_base_path(assigns(:military_base))
  end

  test "should show military_base" do
    get :show, id: @military_base
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @military_base
    assert_response :success
  end

  test "should update military_base" do
    patch :update, id: @military_base, military_base: {  }
    assert_redirected_to military_base_path(assigns(:military_base))
  end

  test "should destroy military_base" do
    assert_difference('MilitaryBase.count', -1) do
      delete :destroy, id: @military_base
    end

    assert_redirected_to military_bases_path
  end
end
