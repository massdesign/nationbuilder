class MilitaryBasesController < ApplicationController
  before_action :set_military_basis, only: [:show, :edit, :update, :destroy]
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
    @military_basis = MilitaryBase.new
  end

  # GET /military_bases/1/edit
  def edit
  end

  # POST /military_bases
  def create
 
    geName = params[:name];
    tile_id = params[:tile_id];
    @gameentity = GameEntity.create(name: geName)
  	 @gameentity.name = geName
    @military_basis = MilitaryBase.new(health: params[:health])
	 if tile_id != nil
		#@military_basis.      
    end	 
	 @gameentity.save  
    @military_basis.game_entity = @gameentity

    respond_to do |format|
    if @military_basis.save
        format.html { redirect_to @military_basis, notice: 'Military basis was successfully created.' }
        format.json { render action: 'id', status: :created, location: @military_basis }
      else
        format.html { render action: 'new' }
        format.json { render json: @military_basis.errors, status: :unprocessable_entity }
      end
	 end  
  end

  # PATCH/PUT /military_bases/1
  def update
    if @military_basis.update(military_basis_params)
      redirect_to @military_basis, notice: 'Military base was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /military_bases/1
  def destroy
    @military_basis.destroy
    redirect_to military_bases_url, notice: 'Military base was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_military_basis
      @military_basis = MilitaryBase.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def military_basis_params
       params.require(:military_basis).permit(:name,:health)
    end
end
