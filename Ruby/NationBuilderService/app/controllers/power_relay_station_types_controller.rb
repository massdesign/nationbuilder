class PowerRelayStationTypesController < ApplicationController
  before_action :set_power_relay_station_type, only: [:show, :edit, :update, :destroy]
     protect_from_forgery :secret => 'salty_phrase',
		       :except => :create


  # GET /power_relay_station_types
  def index
    @power_relay_station_types = PowerRelayStationType.all
  end

  # GET /power_relay_station_types/1
  def show
  end

  # GET /power_relay_station_types/new
  def new
    @power_relay_station_type = PowerRelayStationType.new
  end

  # GET /power_relay_station_types/1/edit
  def edit
  end

  # POST /power_relay_station_types
  def create
    @power_relay_station_type = PowerRelayStationType.new(power_relay_station_type_params)

      respond_to do |format|
      if @power_relay_station_type.save
        format.html { redirect_to @power_relay_station_type, notice: 'Power Relay Station Type was successfully created.' }
        format.json { render action: 'id', status: :created, location: @power_relay_station_type }
      else
        format.html { render action: 'new' }
        format.json { render json: @power_relay_station_type.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /power_relay_station_types/1
  def update
    if @power_relay_station_type.update(power_relay_station_type_params)
      redirect_to @power_relay_station_type, notice: 'Power relay station type was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /power_relay_station_types/1
  def destroy
    @power_relay_station_type.destroy
    redirect_to power_relay_station_types_url, notice: 'Power relay station type was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_power_relay_station_type
      @power_relay_station_type = PowerRelayStationType.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def power_relay_station_type_params
      params.require(:power_relay_station_type).permit(:name, :capacity)
    end
end
