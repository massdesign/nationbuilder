class StatesController < ApplicationController
  before_action :set_state, only: [:show, :edit, :update, :destroy]
    protect_from_forgery :secret => 'salty_phrase',
		       :except => :create

  # GET /states
  def index
    @states = State.all
  end

  # GET /states/1
  def show
  end

  # GET /states/new
  def new
    @state = State.new
  end

  # GET /states/1/edit
  def edit
  end

  # POST /states
  def create
    @state = State.new(state_params)
    if params[:cur] != nil
    @currency = Currency.find(params[:cur])
    
    @currency.states << @state
    end
      respond_to do |format|
      if @state.save
        format.html { redirect_to @state, notice: 'Image was successfully created.' }
        format.json { render action: 'id', status: :created, location: @state }
      else
        format.html { render action: 'new' }
        format.json { render json: @state.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /states/1
  def update
    if @state.update(state_params)
      redirect_to @state, notice: 'State was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /states/1
  def destroy
    @state.destroy
    redirect_to states_url, notice: 'State was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_state
      @state = State.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def state_params
      params.require(:state).permit(:motto, :name,:cur,:reg)
    end
end
