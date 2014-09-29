class MapsController < ApplicationController
  before_action :set_map, only: [:show, :edit, :update, :destroy]

  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /maps
  # GET /maps.json
  def index
    @maps = Map.all
    
    
    #@maps.layers.tiles.joins(:image)
    #@maps.layers
  end
  def create
    @map = Map.new(map_params)

    respond_to do |format|
      if @map.save
        format.html { redirect_to @map, notice: 'Map was successfully created.' }
        format.json { render action: 'id', status: :created, location: @map }
      else
        format.html { render action: 'new' }
        format.json { render json: @map.errors, status: :unprocessable_entity }
      end
    end
  end
  # GET /maps/1
  # GET /maps/1.json
  def show
  end
  # current getscreen supports only one map. If we need more we will implement this
def getscreen
  	 xposrange = params[:width]
	 yposrange = params[:height]  	 
  	 
  	 
  	 @maps = Map.includes([
  	 {
  	 :layers => [:tiles]
  	 }
  	 ]).where(:tiles => { :xposition => params[:centerpositionx]..xposrange,:yposition => params[:centerpositiony]..yposrange})
end
  # GET /maps/new
  def new
    @map = Map.new
  end

  # GET /maps/1/edit
  def edit
  end

  # POST /maps
  # POST /maps.json


  # PATCH/PUT /maps/1
  # PATCH/PUT /maps/1.json
  def update
    respond_to do |format|
      if @map.update(map_params)
        format.html { redirect_to @map, notice: 'Map was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @map.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /maps/1
  # DELETE /maps/1.json
  def destroy
    @map.destroy
    respond_to do |format|
      format.html { redirect_to maps_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_map
      @map = Map.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def map_params
      params.require(:map).permit(:width, :height, :tileWidth, :tileHeight)
    end
end
