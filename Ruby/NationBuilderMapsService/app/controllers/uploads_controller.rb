	class UploadsController < ApplicationController
  before_action :set_upload, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create

  # GET /uploads
  def index
    @uploads = Upload.all
  end

  # GET /uploads/1
  def show
  end

  # GET /uploads/new
  def new
  #  @upload = Upload.new
  end

  # GET /uploads/1/edit
  def edit	
  end

  # POST /uploads
  def create
    render :text => "SUCCESS"
    file  = params[:upload]
     	
     Rails.logger.debug "params upload: " + params[:upload].tempfile.to_s
     Rails.logger.debug "params upload: " + params[:upload].original_filename

	

   # file = params[:all].orginal_filename
  #  @contents = params[:uploaded_file].read
    #render :text => params.to_s
   # name = params[:orginal_filename]
    directory = "public/images/upload"
    path = File.join(directory, file.original_filename)
    
    File.open(path, "wb") { |f| f.write(file.tempfile.read) }
    #flash[:notice] = "File uploaded"
    #redirect_to "/upload/new"
    #if @upload.save
     # redirect_to @upload, notice: 'Upload was successfully created.'
    #else
    #  render action: 'new'
   # end
  end

  # PATCH/PUT /uploads/1
  def update
    if @upload.update(upload_params)
      redirect_to @upload, notice: 'Upload was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /uploads/1
  def destroy
    @upload.destroy
    redirect_to uploads_url, notice: 'Upload was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_upload
      @upload = Upload.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def upload_params
      params[:upload]
    end
end
