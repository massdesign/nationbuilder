class TerrainTypesController < ApplicationController
  before_action :set_terrain_type, only: [:show, :edit, :update, :destroy]

  # GET /terrain_types
  def index
    @terrain_types = TerrainType.all
  end

  # GET /terrain_types/1
  def show
  end

  # GET /terrain_types/new
  def new
    @terrain_type = TerrainType.new
  end

  # GET /terrain_types/1/edit
  def edit
  end

  # POST /terrain_types
  def create
    @terrain_type = TerrainType.new(terrain_type_params)

    if @terrain_type.save
      redirect_to @terrain_type, notice: 'Terrain type was successfully created.'
    else
      render action: 'new'
    end
  end

  # PATCH/PUT /terrain_types/1
  def update
    if @terrain_type.update(terrain_type_params)
      redirect_to @terrain_type, notice: 'Terrain type was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /terrain_types/1
  def destroy
    @terrain_type.destroy
    redirect_to terrain_types_url, notice: 'Terrain type was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_terrain_type
      @terrain_type = TerrainType.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def terrain_type_params
      params.require(:terrain_type).permit(:name)
    end
end
