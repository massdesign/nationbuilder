class EnergyBuildingTypesController < ApplicationController
  before_action :set_energy_building_type, only: [:show, :edit, :update, :destroy]

  protect_from_forgery :secret => 'salty_phrase',
                       :except => :create

  # GET /energy_building_types
  def index
    @energy_building_types = EnergyBuildingType.all
  end

  # GET /energy_building_types/1
  def show
  end

  # GET /energy_building_types/new
  def new
    @energy_building_type = EnergyBuildingType.new
  end

  # GET /energy_building_types/1/edit
  def edit
  end

  # POST /energy_building_types
  def create
    @energy_building_type = EnergyBuildingType.new(energy_building_type_params)
  respond_to do |format|
    if @energy_building_type.save
      format.html { redirect_to @energy_building_type, notice: 'Energybuilding type was successfully created.' }
      format.json { render action: 'id', status: :created, location: @energy_building_type }
    else
      format.html { render action: 'new' }
      format.json { render json: @energy_building_type.errors, status: :unprocessable_entity }
    end
  end
  end

  # PATCH/PUT /energy_building_types/1
  def update
    if @energy_building_type.update(energy_building_type_params)
      redirect_to @energy_building_type, notice: 'Energy building type was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /energy_building_types/1
  def destroy
    @energy_building_type.destroy
    redirect_to energy_building_types_url, notice: 'Energy building type was successfully destroyed.'
  end

  private
  # Use callbacks to share common setup or constraints between actions.
  def set_energy_building_type
    @energy_building_type = EnergyBuildingType.find(params[:id])
  end

  # Only allow a trusted parameter "white list" through.
  def energy_building_type_params
    params.require(:energy_building_type).permit(:name, :powerOutput, :energySource)
  end
end
