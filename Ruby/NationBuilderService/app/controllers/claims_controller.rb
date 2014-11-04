class ClaimsController < ApplicationController
  before_action :set_claim, only: [:show, :edit, :update, :destroy]
  protect_from_forgery :secret => 'salty_phrase',
		       :except => :create
  # GET /claims
  def index
    @claims = Claim.all
  end

  # GET /claims/1
  def show
  end

  # GET /claims/new
  def new
    @claim = Claim.new
  end

  # GET /claims/1/edit
  def edit
  end

  # POST /claims
  def create
    @claim = Claim.new(claim_params)
    if params[:tile_id] != nil
      @tile = Tile.find(params[:tile_id])
      @tile.claims << @claim
    end
    if params[:state_id] != nil
      @state = State.find(params[:state_id])
      @state.claims << @claim
    end
    
    respond_to do |format|

      if @claim.save 
        format.html { redirect_to @claim, notice: 'Claim was successfully created.' }
        format.json { render action: 'id', status: :created, location: @claim  }
      else
        format.html { render action: 'new' }
        format.json { render json: @claim.errors, status: :unprocessable_entity }
      end
    end
  end

  # PATCH/PUT /claims/1
  def update
    if @claim.update(claim_params)
      redirect_to @claim, notice: 'Claim was successfully updated.'
    else
      render action: 'edit'
    end
  end

  # DELETE /claims/1
  def destroy
    @claim.destroy
    redirect_to claims_url, notice: 'Claim was successfully destroyed.'
  end

  private
    # Use callbacks to share common setup or constraints between actions.
    def set_claim
      @claim = Claim.find(params[:id])
    end

    # Only allow a trusted parameter "white list" through.
    def claim_params
      params.require(:claim).permit(:tile_id, :state_id)
    end
end
