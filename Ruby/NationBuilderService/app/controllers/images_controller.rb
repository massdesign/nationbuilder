class ImagesController < ApplicationController
  before_action :set_image, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /images
  # GET /images.json
  def index
    @images = Image.all
  end



  # GET /images/1
  # GET /images/1.json
  def show
  end

  # GET /images/new
  def new
    @image = Image.new
  end

  # GET /images/1/edit
  def edit
  end
  # POST /images
  # POST /images.json
  def create
    @image = Image.new(image_params)
	 @map = Map.find(params[:mid])
	 @map.images << @image
	 
    respond_to do |format|
      if @image.save
        format.html { redirect_to @image, notice: 'Image was successfully created.' }
        format.json { render action: 'id', status: :created, location: @image }
      else
        format.html { render action: 'new' }
        format.json { render json: @image.errors, status: :unprocessable_entity }
      end
    end
  end
  
  def getscreen
  x1 = params[:x1]
  y1 = params[:y1]
  
  x2 = params[:x2]
  y2 = params[:y2]
  
   #@images = Image.joins("INNER JOIN tiles ON tiles.image_id = images.id WHERE tiles.xposition = 5 GROUP BY images.id ")
   @images = Image.includes(:tiles).where("tiles.xposition" => x1..x2,"tiles.yposition" => y1..y2)
  

   
 #	@tiles = Tile.joins("INNER JOIN images ON images.id = tiles.image_id").find(:all,:conditions => 
  #	 { :xposition => x1..x2,
  	#   :yposition => y1..y2
  	# })
  	 
  	 #for image in @images 
  	 #image.tiles = @tiles
	 #end
   
  end
  # PATCH/PUT /images/1
  # PATCH/PUT /images/1.json
  def update
    respond_to do |format|
      if @image.update(image_params)
        format.html { redirect_to @image, notice: 'Image was successfully updated.' }
        format.json { head :no_content }
      else
        format.html { render action: 'edit' }
        format.json { render json: @image.errors, status: :unprocessable_entity }
      end
    end
  end

  # DELETE /images/1
  # DELETE /images/1.json
  def destroy
    @image.destroy
    respond_to do |format|
      format.html { redirect_to images_url }
      format.json { head :no_content }
    end
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_image
      @image = Image.find(params[:id])
    end

    # Never trust parameters from the scary internet, only allow the white list through.
    def image_params
      params.require(:image).permit(:name,:url,:width,:height,:resource)
    end
end
