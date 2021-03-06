class UsersController < ApplicationController
  before_action :set_user, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /users
  def index
    @users = User.all
  end

  # GET /users/1
  def show	
  end

  # GET /users/new
  def new
    @user = User.new
  end
  def getuserbyname
  	if params[:name] != nil
	 @user = User.where(loginname: params[:name]).take  
  	end
  end
  # GET /users/1/edit
  def edit
  end

  # POST /users
  def create
    @user = User.new(user_params)
    if params[:ge] != nil
  	 	@state = State.find(params[:ge])
    	@user.state = @state
    end
   respond_to do |format|
		if @user.save
			format.json { render action: 'id', status: :created, location: @user }
		else
		  render action: 'new'
		end
   end
	
  end

  # PATCH/PUT /users/1
  def update
    if @user.update(user_params)
      redirect_to @user, notice: 'User was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /users/1
  def destroy
    @user.destroy
    redirect_to users_url, notice: 'User was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_user
      @user = User.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def user_params
      params.require(:user).permit(:screenname, :loginname, :passwordhash, :registerdate, :emailadres,:ge)
    end
end
