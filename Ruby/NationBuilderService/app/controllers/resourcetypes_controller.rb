class ResourcetypesController < ApplicationController
  before_action :set_resourcetype, only: [:show, :edit, :update, :destroy]

  # GET /resourcetypes
  def index
    @resourcetypes = Resourcetype.all
  end

  # GET /resourcetypes/1
  def show
  end

  # GET /resourcetypes/new
  def new
    @resourcetype = Resourcetype.new
  end

  # GET /resourcetypes/1/edit
  def edit
  end

  # POST /resourcetypes
  def create
    @resourcetype = Resourcetype.new(resourcetype_params)

    if @resourcetype.save
      redirect_to @resourcetype, notice: 'Resourcetype was successfully created.'
    else
      render action: 'new'
    end
  end

  # PATCH/PUT /resourcetypes/1
  def update
    if @resourcetype.update(resourcetype_params)
      redirect_to @resourcetype, notice: 'Resourcetype was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /resourcetypes/1
  def destroy
    @resourcetype.destroy
    redirect_to resourcetypes_url, notice: 'Resourcetype was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_resourcetype
      @resourcetype = Resourcetype.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def resourcetype_params
      params[:resourcetype]
    end
end
