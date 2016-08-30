class PowerRelayStationsController < ApplicationController
  before_action :set_power_relay_station, only: [:show, :edit, :update, :destroy]
    protect_from_forgery :secret => 'salty_phrase',
		       :except => :create


  # GET /power_relay_stations
  def index
    @power_relay_stations = PowerRelayStation.all
  end

  # GET /power_relay_stations/1
  def show
  end

  # GET /power_relay_stations/new
  def new
    @power_relay_station = PowerRelayStation.new
  end

  # GET /power_relay_stations/1/edit
  def edit
  end

  # POST /power_relay_stations
  def create
     #@power_relay_station = PowerRelayStation.new(power_relay_station_params)
     @power_relay_station = PowerRelayStation.new()
     bName = params[:name];
     
	  @building = Building.create(name: bName)
     if params[:geo] != nil      
	  	@gameentity = GameEntity.find(params[:geo])
	  	@gameentity.buildings << @building
	  end
	  if params[:prstid] != nil      
	  	@power_relay_station_type = PowerRelayStationType.find(params[:prstid])
	  	@power_relay_station	.power_relay_station_type = @power_relay_station_type
	  end
	   
	  @building.save
	  @power_relay_station.building = @building
       respond_to do |format|
      if @power_relay_station.save
        format.html { redirect_to @power_relay_station, notice: 'Power Relay Station was successfully created.' }
        format.json { render action: 'id', status: :created, location: @power_relay_station }
      else
        format.html { render action: 'new' }
        format.json { render json: @power_relay_station.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /power_relay_stations/1
  def update
    if @power_relay_station.update(power_relay_station_params)
      redirect_to @power_relay_station, notice: 'Power relay station was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /power_relay_stations/1
  def destroy
    @power_relay_station.destroy
    redirect_to power_relay_stations_url, notice: 'Power relay station was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_power_relay_station
      @power_relay_station = PowerRelayStation.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def power_relay_station_params
      params.require(:power_relay_station).permit(:capacity)
    end
end
