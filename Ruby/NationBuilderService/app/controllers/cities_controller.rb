		class CitiesController < ApplicationController
  before_action :set_city, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /cities
  def index
    @cities = City.all
  end

  # GET /cities/1
  def show
  end

  # GET /cities/new
  def new
    @city = City.new
  end

  # GET /cities/1/edit
  def edit
  end

  # POST /cities
  def create
  	 geName = params[:name];
  	 @gameentity = GameEntity.create(name: geName)
  	 @gameentity.name = geName
	
	 

    @city = City.new(city_params)
	 @gameentity.save  
    @city.game_entity = @gameentity
    
    
    
 	 respond_to do |format|
    if @city.save
        format.html { redirect_to @city, notice: 'City was successfully created.' }
        format.json { render action: 'id', status: :created, location: @city }
      else
        format.html { render action: 'new' }
        format.json { render json: @city.errors, status: :unprocessable_entity }
      end
	 end  
  end

  # PATCH/PUT /cities/1
  def update
    if @city.update(city_params)
      redirect_to @city, notice: 'City was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /cities/1
  def destroy
    @city.destroy
    redirect_to cities_url, notice: 'City was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_city
      @city = City.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def city_params
     # params.require(:city).permit(:name, :buildings, :connections, :locations, :contracts)
      params.require(:city).permit(:population,:name)
    end
end
