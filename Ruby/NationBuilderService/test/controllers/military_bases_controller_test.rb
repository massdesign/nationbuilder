require 'test_helper'

class MilitaryBasesControllerTest < ActionController::TestCase
  setup do
    @military_basis = military_bases(:one)
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

  test "should create military_basis" do
    assert_difference('MilitaryBase.count') do
      post :create, military_basis: {  }
    end

    assert_redirected_to military_basis_path(assigns(:military_basis))
  end

  test "should show military_basis" do
    get :show, id: @military_basis
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @military_basis
    assert_response :success
  end

  test "should update military_basis" do
    patch :update, id: @military_basis, military_basis: {  }
    assert_redirected_to military_basis_path(assigns(:military_basis))
  end

  test "should destroy military_basis" do
    assert_difference('MilitaryBase.count', -1) do
      delete :destroy, id: @military_basis
    end

    assert_redirected_to military_bases_path
  end
end
