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
  
  def fetchsections
  	# NOTE  X en Y  zijn wat in javascript xOuter en yOuter zijn
		# TODO: Deze moeten we uit config.js halen.. Hier moeten we nog wat op vinden  		
  		@section_width = 7
  		@section_height = 7
  		@lowestX = -1 
  		@lowestY = -1
  		
  		@highestX = -1
  		@highestY = -1 
  		
  		
  		@tiles = Array.new  	  		
	   params[:_json].each  do  |i| 
			
			#logger.info  "zou die het doen?" + i.to_s	   
			logger.info "X=" + i[:X].to_s
			logger.info "Y=" + i[:Y].to_s
			if i[:X] > @highestX
				@highestX = i[:X]
			end
			if i[:Y] > @highestY
				@highestY = i[:Y]		  
			end
			 
			if i[:X] != -1 and i[:X] < @lowestX
				@lowestX = i[:X]
			elsif @lowestX == -1 
				@lowestX = i[:X]			
			end
		
			if i[:Y] != -1 and i[:Y] < @lowestY
				@lowestY = i[:Y]			
			elsif @lowestY == -1
				@lowestY = i[:Y] 		
			end
			# we gaan er even vanuit dat dit klopt. 			
				
				
				

				

			#elsif @lowestX == -1
			#	@lowestX = i[:X]
			

			
			#@xOuter1 = i[:X]
			#@yOuter1 = i[:Y]
			#@xOuter2 = @xOuter1 + @section_width
			#@yOuter2 = @yOuter1 + @section_height 			
			
			# Eerst situatie maken zodat alle elke sectie in zijn eigen query opgehaald word. Als dat werkt kunnen we kijken naar optimalisatie
			#@tiles += Tile.find(:all,:conditions => 
  	 		#{ :xposition => @xOuter1..@xOuter2,
  	   	#  :yposition => @yOuter1..@yOuter2
  	 		#})
  	 		#  logger.info "Collected tiles" + @tiles.size.to_s
  	 			
	   end			  
	   	logger.info "Lowest  X: " + @lowestX.to_s	
			logger.info "Lowest  Y: " + @lowestY.to_s	
			logger.info "Highest X: " + @highestX.to_s	
			logger.info "Highest Y: " + @highestY.to_s	
							
	   	@highestX += 3
			@highestY += 3
			@maps = Map.includes([
  	 		{
  	 		 :layers => [:tiles]
  	 		}
  	 		]).where(:tiles => { :xposition => @lowestX..@highestX,:yposition => @lowestY..@highestY})
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
