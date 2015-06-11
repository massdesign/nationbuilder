class MilitarystrongholdsController < ApplicationController
  before_action :set_militarystronghold, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /militarystrongholds
  def index
    @militarystrongholds = Militarystronghold.all
  end

  # GET /militarystrongholds/1
  def show
  end

  # GET /militarystrongholds/new
  def new
    @militarystronghold = Militarystronghold.new
  end

  # GET /militarystrongholds/1/edit
  def edit
  end
  
  def getscreen
  
  end 
  
  def createnewbase
  
   geName = params[:name];
    tile_id = params[:tile_id];
    @gameentity = GameEntity.create(name: geName)
  	 @gameentity.name = geName
    @militarystronghold = Militarystronghold.new(health: params[:health])	
	
 	if tile_id != nil	 
	 @tile = Tile.find(tile_id)
	 	# puts  "even testen of dit uberhaupt wel iets doet" + @tile.militarystrongholds.length.to_s
	 if @tile.militarystrongholds.length == 0
	 		puts  "Hier is nog geen military base geplaatst dus het is toegestaan om te plaatsen"
	 		@militarystronghold.tiles  << @tile    
			# @tile.militarystrongholds << @military_base    
        
     
	 	@gameentity.save  
    	@militarystronghold.game_entity = @gameentity

    	respond_to do |format|
    		if  @militarystronghold.save
        		format.html { redirect_to  @militarystronghold, notice: 'Military basis was successfully created.' }
        		format.json { render action: 'createnewbase', status: :created, location:  @militarystronghold }
      	else
        		format.html { render action: 'new' }
        		format.json { render json:  @militarystronghold.errors, status: :unprocessable_entity }
      	end
	  end
	  else 
	  	puts "tile wordt al gebruikt voor een military base"	
	  	@tilestatus = Tilestatus.new()
	  	@tilestatus.status = "ALREADYINUSE"
	  	respond_to do |format|
		 format.json { render action: 'alreadyinuse', status: :created }#, #location: @tilestatus}
		 end
	  end	 
	 end   
  end

  # POST /militarystrongholds
  def create
 
    geName = params[:name];
    tile_id = params[:tile_id];
    @gameentity = GameEntity.create(name: geName)
  	 @gameentity.name = geName
    @militarystronghold = Militarystronghold.new(health: params[:health])	 
	 @gameentity.save  
    @militarystronghold.game_entity = @gameentity

    respond_to do |format|
    if  @militarystronghold.save
        format.html { redirect_to  @militarystronghold, notice: 'Military basis was successfully created.' }
        format.json { render action: 'id', status: :created, location:  @militarystronghold }
      else
        format.html { render action: 'new' }
        format.json { render json:  @militarystronghold.errors, status: :unprocessable_entity }
      end
	 end  
  end


  # PATCH/PUT /militarystrongholds/1
  def update
    if @militarystronghold.update(militarystronghold_params)
      redirect_to @militarystronghold, notice: 'Militarystronghold was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /militarystrongholds/1
  def destroy
    @militarystronghold.destroy
    redirect_to militarystrongholds_url, notice: 'Militarystronghold was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_militarystronghold
      @militarystronghold = Militarystronghold.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def militarystronghold_params
      params[:militarystronghold]
    end
end
