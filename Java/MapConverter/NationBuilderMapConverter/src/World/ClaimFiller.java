package World;

import nationbuilder.lib.Ruby.Interfaces.RubyObjectFactory;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 11/2/14.
 */
public class ClaimFiller extends BaseFiller {
    private List<Tile> tiles;

    private RubyObjectFactory<State> stateObjectFactory;

    public ClaimFiller(RubyContext context) {
        super(context);

        this.stateObjectFactory = context.createRubyObjectFacory(State.class,State[].class);
    }
    public void setTiles(List<Tile> tiles)
    {
        this.tiles = tiles;
    }

    private MapDataset mapDataset;
    @Override
    public void Fill() {

      State state =  this.stateObjectFactory.get(1);

        List<Tile> rockTiles = this.getRockTiles();
        for(Tile tile : rockTiles)
        {
            this.getRubyModels().add(createClaim(tile,state));
        }
    }

    public List<Tile> getRockTiles()
    {
        List<Tile> result = new ArrayList<Tile>();
        for(Tile tile : this.mapDataset.getMapTiles())
        {
            TerrainType terrainType =   getTerrainType(tile);
            if(terrainType != null && terrainType.getName().equals("MOUNTAIN"))
            {
                result.add(tile);
            }
        }
        return result;
    }
    private TerrainType getTerrainType(Tile tile)
    {
        TerrainType result = null;
            if(tile.getTerrainType() != null)
            {
                result = tile.getTerrainType();
            }
        return result;
    }

    public Claim createClaim(Tile tile,State state)
    {
        Claim newClaim = this.getContext().createRubyModel(Claim.class);
        newClaim.setClaimedBy(state);
        newClaim.setClaimedTile(tile);
        return newClaim;
    }

    public MapDataset getMapDataset() {
        return mapDataset;
    }

    public void setMapDataset(MapDataset mapDataset) {
        this.mapDataset = mapDataset;
    }


}
