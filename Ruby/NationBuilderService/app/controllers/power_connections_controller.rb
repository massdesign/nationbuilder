class PowerConnectionsController < ApplicationController
  before_action :set_power_connection, only: [:show, :edit, :update, :destroy]
    protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /power_connections
  def index
    @power_connections = PowerConnection.all
  end

  # GET /power_connections/1
  def show
  end

  # GET /power_connections/new
  def new
    @power_connection = PowerConnection.new
  end

  # GET /power_connections/1/edit
  def edit
  end

  # POST /power_connections
  def create
    @power_connection = PowerConnection.new(power_connection_params)
	  if params[:aid] != nil      
	  	@power_grid_node_a = PowerGridNode.find(params[:aid])
	  	@power_connection.power_grid_node_a = @power_grid_node_a
	  	#@gameentity.buildings << @building
	  end
	  if params[:bid] != nil      
	  	@power_grid_node_b = PowerGridNode.find(params[:bid])
	   @power_connection.power_grid_node_b = @power_grid_node_b
	  end
      respond_to do |format|
      if @power_connection.save
        format.html { redirect_to @power_connection, notice: 'Power Connection was successfully created.' }
        format.json { render action: 'id', status: :created, location: @power_connection }
      else
        format.html { render action: 'new' }
        format.json { render json: @power_connection.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /power_connections/1
  def update
    if @power_connection.update(power_connection_params)
      redirect_to @power_connection, notice: 'Power connection was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /power_connections/1
  def destroy
    @power_connection.destroy
    redirect_to power_connections_url, notice: 'Power connection was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_power_connection
      @power_connection = PowerConnection.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def power_connection_params
      params.require(:power_connection).permit(:load, :capacity, :name)
    end
end
