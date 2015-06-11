require 'test_helper'

class MilitarystrongholdsControllerTest < ActionController::TestCase
  setup do
    @militarystronghold = militarystrongholds(:one)
  end

  test "should get index" do
    get :index
    assert_response :success
    assert_not_nil assigns(:militarystrongholds)
  end

  test "should get new" do
    get :new
    assert_response :success
  end

  test "should create militarystronghold" do
    assert_difference('Militarystronghold.count') do
      post :create, militarystronghold: {  }
    end

    assert_redirected_to militarystronghold_path(assigns(:militarystronghold))
  end

  test "should show militarystronghold" do
    get :show, id: @militarystronghold
    assert_response :success
  end

  test "should get edit" do
    get :edit, id: @militarystronghold
    assert_response :success
  end

  test "should update militarystronghold" do
    patch :update, id: @militarystronghold, militarystronghold: {  }
    assert_redirected_to militarystronghold_path(assigns(:militarystronghold))
  end

  test "should destroy militarystronghold" do
    assert_difference('Militarystronghold.count', -1) do
      delete :destroy, id: @militarystronghold
    end

    assert_redirected_to militarystrongholds_path
  end
end
