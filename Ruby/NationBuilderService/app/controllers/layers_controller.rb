class LayersController < ApplicationController
  before_action :set_layer, only: [:show, :edit, :update, :destroy]

  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /layers
  # GET /layers.json
  def index
    @layers = Layer.all
     respond_to do |format|
     format.html # show.html.erb
     format.json
    end
    
  end

  # GET /layers/1
  # GET /layers/1.json
  def show
    @layer = Layer.find(params[:id])
    respond_to do |format|
     format.html # show.html.erb
     format.json
     end
     
  end

  # GET /layers/new
  def new
    @layer = Layer.new
  end

  # GET /layers/1/edit
  def edit
  end

  # POST /layers
  # POST /layers.json
  def create
    @layer = Layer.new(layer_params)
	 @map = Map.find(params[:mid])
	 
	 @map.layers << @layer
	 
    respond_to do |format|
      if @layer.save
        format.html { redirect_to @layer, notice: 'Layer was successfully created.' }
        format.json { render action: 'id', status: :created, location: @layer }
      else
        format.html { render action: 'new' }
        format.json { render json: @layer.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /layers/1
  # PATCH/PUT /layers/1.json
  def update
    respond_to do |format|
      if @layer.update(layer_params)
        format.html { redirect_to @layer, notice: 'Layer was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @layer.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /layers/1
  # DELETE /layers/1.json
  def destroy
    @layer.destroy
    respond_to do |format|
      format.html { redirect_to layers_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_layer
      @layer = Layer.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def layer_params
      params.require(:layer).permit(:name,:zindex)
    end
end
