class NodeTypesController < ApplicationController
  before_action :set_node_type, only: [:show, :edit, :update, :destroy]
   protect_from_forgery :secret => 'salty_phrase',
		       :except => :create


  # GET /node_types
  def index
    @node_types = NodeType.all
  end

  # GET /node_types/1
  def show
  end

  # GET /node_types/new
  def new
    @node_type = NodeType.new
  end

  # GET /node_types/1/edit
  def edit
  end

  # POST /node_types
  def create
    @node_type = NodeType.new(node_type_params)

     respond_to do |format|
      if @node_type.save
        format.html { redirect_to @node_type, notice: 'Nodetype was successfully created.' }
        format.json { render action: 'id', status: :created, location: @state }
      else
        format.html { render action: 'new' }
        format.json { render json: @node_type.errors, status: :unprocessable_entity }
      end
    end
  
  end

  # PATCH/PUT /node_types/1
  def update
    if @node_type.update(node_type_params)
      redirect_to @node_type, notice: 'Node type was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /node_types/1
  def destroy
    @node_type.destroy
    redirect_to node_types_url, notice: 'Node type was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_node_type
      @node_type = NodeType.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def node_type_params
      params.require(:node_type).permit(:name, :destroyable)
    end
end
