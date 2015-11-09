class ResourcesController < ApplicationController
  before_action :set_resource, only: [:show, :edit, :update, :destroy]
  
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /resources
  def index
    @resources = Resource.all
  end

  # GET /resources/1
  def show
  end

  # GET /resources/new
  def new
    @resource = Resource.new
  end

  # GET /resources/1/edit
  def edit
  end

  # POST /resources
  def create
    @resource = Resource.new(resource_params)
    
    #@terraintype = Terraintype.find(params[:tti])
    
	if params[:rti] != nil
 			 @resourcetype = Resourcetype.find(params[:rti])	 
			 @resource.resourcetype  =  @resourcetype		
	end    
  
    respond_to do |format|
      if @resource.save
       format.html { redirect_to @resource, notice: 'Resource was successfully created.' }
        format.json { render action: 'id', status: :created, location: @resource }
      else
        format.html { render action: 'new' }
        format.json { render json: @resource.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /resources/1
  def update
    if @resource.update(resource_params)
      redirect_to @resource, notice: 'Resource was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /resources/1
  def destroy
    @resource.destroy
    redirect_to resources_url, notice: 'Resource was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_resource
      @resource = Resource.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def resource_params
      params[:resource]
    end
end
