class TerraintypesController < ApplicationController
  before_action :set_terraintype, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /terraintypes
  def index
    @terraintypes = Terraintype.all
  end

  # GET /terraintypes/1
  def show
  end

  # GET /terraintypes/new
  def new
    @terraintype = Terraintype.new
  end

  # GET /terraintypes/1/edit
  def edit
  end

  # POST /terraintypes
  def create
    @terraintype = Terraintype.new(terraintype_params)
    respond_to do |format|
     if @terraintype.save
       format.html { redirect_to @terraintype, notice: 'Terraintype was successfully created.' }
       format.json { render action: 'id', status: :created, location: @terraintype }
     else
       render action: 'new'
     end
   end
  end

  # PATCH/PUT /terraintypes/1
  def update
    if @terraintype.update(terraintype_params)
      redirect_to @terraintype, notice: 'Terraintype was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /terraintypes/1
  def destroy
    @terraintype.destroy
    redirect_to terraintypes_url, notice: 'Terraintype was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_terraintype
      @terraintype = Terraintype.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def terraintype_params
      params.require(:terraintype).permit(:name)
    end
end
