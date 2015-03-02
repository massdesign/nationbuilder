class WarehousesController < ApplicationController
  before_action :set_warehouse, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /warehouses
  def index
    @warehouses = Warehouse.all
  end

  # GET /warehouses/1
  def show
  end

  # GET /warehouses/new
  def new
    @warehouse = Warehouse.new
  end

  # GET /warehouses/1/edit
  def edit
  end

  # POST /warehouses
  def create
    @warehouse = Warehouse.new(warehouse_params)

   respond_to do |format|
    if @warehouse.save
        format.html { redirect_to @warehouse, notice: 'City was successfully created.' }
        format.json { render action: 'id', status: :created, location: @warehouse }
      else
        format.html { render action: 'new' }
        format.json { render json: @warehouse.errors, status: :unprocessable_entity }
      end
	 end  
  end

  # PATCH/PUT /warehouses/1
  def update
    if @warehouse.update(warehouse_params)
      redirect_to @warehouse, notice: 'Warehouse was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /warehouses/1
  def destroy
    @warehouse.destroy
    redirect_to warehouses_url, notice: 'Warehouse was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_warehouse
      @warehouse = Warehouse.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def warehouse_params
      params[:warehouse]
    end
end
