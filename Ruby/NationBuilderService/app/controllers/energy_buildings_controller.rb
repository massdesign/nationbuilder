class EnergyBuildingsController < ApplicationController
  before_action :set_energy_building, only: [:show, :edit, :update, :destroy]

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

      if @energy_building.save
       format.html { redirect_to @energy_building, notice: 'Terraintype was successfully created.' }
       format.json { render action: 'id', status: :created, location: @energy_building }
     else
       render action: 'new'
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
      params.require(:energy_building).permit(:name, :poweroutput, :type)
    end
end
