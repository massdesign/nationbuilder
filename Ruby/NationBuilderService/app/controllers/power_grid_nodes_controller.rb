class PowerGridNodesController < ApplicationController
  before_action :set_power_grid_node, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create


  # GET /power_grid_nodes
  def index
    @power_grid_nodes = PowerGridNode.all
  end

  # GET /power_grid_nodes/1
  def show
  end

  # GET /power_grid_nodes/new
  def new
    @power_grid_node = PowerGridNode.new
  end

  # GET /power_grid_nodes/1/edit
  def edit
  end

  # POST /power_grid_nodes
  def create
  	 nTName = params[:name];
  	 nTDestroyable  = params[:destroyable]
  	 
  	 @nodetype = NodeType.create(name: nTName,destroyable: nTDestroyable)	
    @power_grid_node = PowerGridNode.new(power_grid_node_params)
    @nodetype.save 
	 @power_grid_node.node_type = @nodetype 
	 if params[:rsid] != nil      
	  @powerrelaystation = PowerRelayStation.find(params[:rsid])
	  @powerrelaystation.power_grid_node = @power_grid_node
	 end
	 
      respond_to do |format|
      if @power_grid_node.save
        format.html { redirect_to @power_grid_node, notice: 'PowerGridNode was successfully created.' }
        format.json { render action: 'id', status: :created, location: @power_grid_node }
      else
        format.html { render action: 'new' }
        format.json { render json: @power_grid_node.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /power_grid_nodes/1
  def update
    if @power_grid_node.update(power_grid_node_params)
      redirect_to @power_grid_node, notice: 'Power grid node was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /power_grid_nodes/1
  def destroy
    @power_grid_node.destroy
    redirect_to power_grid_nodes_url, notice: 'Power grid node was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_power_grid_node
      @power_grid_node = PowerGridNode.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def power_grid_node_params
      params[:power_grid_node]
    end
end
