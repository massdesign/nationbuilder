class TilesController < ApplicationController

  before_action :set_tile, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /tiles
  # GET /tiles.json
  def index
    @tiles = Tile.all
  end

  # GET /tiles/1
  # GET /tiles/1.json
  def show
  end
  # NOTE: verplaatst naar maps_controller
 # def fetchsections
  	# NOTE  X en Y  zijn wat in javascript xOuter en yOuter zijn
		# TODO: Deze moeten we uit config.js halen.. Hier moeten we nog wat op vinden  		
  #		@section_width = 7
  	#	@section_height = 7
  		
  	#	@tiles = Array.new  	  		
	 #  params[:_json].each  do  |i| 
			
			#logger.info  "zou die het doen?" + i.to_s	   
		#	logger.info "X=" + i[:X].to_s
		#	logger.info "Y=" + i[:Y].to_s
		#	@xOuter1 = i[:X]
		#	@yOuter1 = i[:Y]
		#	@xOuter2 = @xOuter1 + @section_width
		#	@yOuter2 = @yOuter1 + @section_height 			
			
			# Eerst situatie maken zodat alle elke sectie in zijn eigen query opgehaald word. Als dat werkt kunnen we kijken naar optimalisatie
		#	@tiles += Tile.find(:all,:conditions => 
  	 	#	{ :xposition => @xOuter1..@xOuter2,
  	   #	  :yposition => @yOuter1..@yOuter2
  	 	#	})
  	 	#	  logger.info "Collected tiles" + @tiles.size.to_s
  	 		
	   #end			  
 # end
  # NOTE: deze getscreen methode wordt nu gebruikt blijkbaar, deze kan dus weg 
  def getscreen
  	 xposrange = params[:width]
	 yposrange = params[:height]  	 
  	 @tiles = Tile.find(:all,:conditions => 
  	 { :xposition => params[:centerpositionx]..xposrange,
  	   :yposition => params[:centerpositiony]..yposrange
  	 })
  	 #@tiles = Tile.all.where(xposition: params[:centerpositionx],yposition: params[:centerpositiony])
  	 
     #  @tile = Tile.where(xposition: 0,yposition: 0).take
     #respond_to do |format|
 		#format.json { render action: 'getscreen', status: :created, location: @tile }
	 #end
  end
  # GET /tiles/foo
  def find 
  @tile = Tile.joins("LEFT JOIN claims ON tiles.id = claims.tile_id").joins(:resource).where(xposition: params[:xposition],yposition: params[:yposition]).take
  
  #@resource = Resource.joins(:resourcetype).joins(:tiles).where(id: @tile.resource_id).take
	#@resources = Resource.joins(:resourcetype).joins(:tile).where(tile_id: @tile.id)
   #respond_to do |format|
 	#	format.json { render action: 'find', status: :created, location: @resources }
	# end
  end
  # GET /tiles/new
  def new
    @tile = Tile.new
  end

  # GET /tiles/1/edit
  def edit
  end

  # POST /tiles
  # POST /tiles.json
  def create
ActionController::Parameters.permit_all_parameters = true
    @tile = Tile.new(tile_params)

    if params[:imd] != nil
      @image = Image.find(params[:imd])
      @image.tiles << @tile
    end
    if params[:tti] != nil
		@terraintype = Terraintype.find(params[:tti])   
		@terraintype.tiles << @tile
		#@tile.terraintype = @terraintype 
    end
    if params[:lmd] != nil
      @layer = Layer.find(params[:lmd])
      @layer.tiles << @tile
    end

    if params[:rid] != nil
    	@resource = Resource.find(params[:rid])
	   @resource.tiles << @tile
	 end 
    if params[:rids] != nil
		params[:rids].each {	|x| 
	 	@resource = Resource.find(x)	 
	 	@tile.resources << @resource
		}
	end		
Rails.logger.debug "tile params: " + params[:imd].to_s
  
    respond_to do |format|

      if @tile.save 
        format.html { redirect_to @tile, notice: 'Tile was successfully created.' }
        format.json { render action: 'id', status: :created, location: @tile }
      else
        format.html { render action: 'new' }
        format.json { render json: @tile.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /tiles/1
  # PATCH/PUT /tiles/1.json
  def update
    respond_to do |format|
      if @tile.update(tile_params)
        format.html { redirect_to @tile, notice: 'Tile was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @tile.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /tiles/1
  # DELETE /tiles/1.json
  def destroy
    @tile.destroy
    respond_to do |format|
      format.html { redirect_to tiles_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_tile
      @tile = Tile.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def tile_params
      params.require(:tile).permit(:gidtag,:xposition, :yposition, :xoffset, :yoffset)
    end
end
