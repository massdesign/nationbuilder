class MilitaryBasesController < ApplicationController
  before_action :set_military_base, only: [:show, :edit, :update, :destroy]
    protect_from_forgery :secret => 'salty_phrase',
		       :except => :create

  # GET /military_bases
  def index
    @military_bases = MilitaryBase.all
  end

  # GET /military_bases/1
  def show
  end

  # GET /military_bases/new
  def new
    @military_base = MilitaryBase.new
  end

  # GET /military_bases/1/edit
  def edit
  end
  # POST /military_bases/createnewbase create military base with resides on one tile.. datamodel is suited for many to many relationship
  def createnewbase
  
	 geName = params[:name];
    tile_id = params[:tile_id];
    @gameentity = GameEntity.create(name: geName)
  	 @gameentity.name = geName
    @military_base = MilitaryBase.new(health: params[:health])
	 if tile_id != nil
	 
	 @tile = Tile.find(tile_id)
	# @military_base.tiles  << @tile    
	 @tile.military_bases << @military_base    
	 logger.info(ActiveSupport::Inflector.pluralize("militarybase"))
    end	 
	 @gameentity.save  
    @military_base.game_entity = @gameentity

    respond_to do |format|
    if @military_base.save
        format.html { redirect_to @military_base, notice: 'Military basis was successfully created.' }
        format.json { render action: 'id', status: :created, location: @military_base }
      else
        format.html { render action: 'new' }
        format.json { render json: @military_base.errors, status: :unprocessable_entity }
      end
	 end

  
  end

  # POST /military_bases
  def create
 
    geName = params[:name];
    tile_id = params[:tile_id];
    @gameentity = GameEntity.create(name: geName)
  	 @gameentity.name = geName
    @military_base = MilitaryBase.new(health: params[:health])	 
	 @gameentity.save  
    @military_base.game_entity = @gameentity

    respond_to do |format|
    if @military_base.save
        format.html { redirect_to @military_base, notice: 'Military basis was successfully created.' }
        format.json { render action: 'id', status: :created, location: @military_base }
      else
        format.html { render action: 'new' }
        format.json { render json: @military_base.errors, status: :unprocessable_entity }
      end
	 end  
  end

  # PATCH/PUT /military_bases/1
  def update
    if @military_base.update(military_base_params)
      redirect_to @military_base, notice: 'Military base was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /military_bases/1
  def destroy
    @military_base.destroy
    redirect_to military_bases_url, notice: 'Military base was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_military_base
      @military_base = MilitaryBase.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def military_base_params
       params.require(:military_base).permit(:name,:health)
    end
end
