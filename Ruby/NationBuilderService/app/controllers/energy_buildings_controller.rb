class EnergyBuildingsController < ApplicationController
  before_action :set_energy_building, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
                       :except => :create
  # GET /energy_buildings
  def index
    @energy_buildings = EnergyBuilding.all
  end

  # GET /energy_buildings/1
  def show
  end

  # GET /energy_buildings/new
  def new
    @energy_building = EnergyBuilding.new
  end

  # GET /energy_buildings/1/edit
  def edit
  end

  # POST /energy_buildings
  def create
    @energy_building = EnergyBuilding.new(energy_building_params)

    if params[:btid] != nil
      @energy_building_type = EnergyBuildingType.find(params[:btid])
      @energy_building.energy_building_type = @energy_building_type
    end
    if params[:loid] != nil
      @tile = Tile.find(params[:loid])
      @energy_building.tile = @tile
    end
    respond_to do |format|
      if @energy_building.save
        format.html { redirect_to @energy_building, notice: 'Energybuilding was successfully created.' }
        format.json { render action: 'id', status: :created, location: @energy_building }
      else
        format.html { render action: 'new' }
        format.json { render json: @energy_building.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /energy_buildings/1
  def update
    if @energy_building.update(energy_building_params)
      redirect_to @energy_building, notice: 'Energy building was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /energy_buildings/1
  def destroy
    @energy_building.destroy
    redirect_to energy_buildings_url, notice: 'Energy building was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_energy_building
      @energy_building = EnergyBuilding.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def energy_building_params
      params.require(:energy_building).permit(:name)
    end
end
