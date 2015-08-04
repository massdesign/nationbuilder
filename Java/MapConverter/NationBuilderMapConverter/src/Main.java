
import java.io.IOException;

import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.data.map.entities.MilitaryStronghold;
import nationbuilder.lib.data.map.entities.Resource;
import nationbuilder.lib.data.map.entities.ResourceType;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.entities.Tile;


public class Main {
	public static void main(String[] args) throws IOException, RubyException
    {

        RubyContext context = new RubyContextFactory().createRubyContext();

        WorldLoader worldLoader = new WorldLoader(context);
        worldLoader.Run();

      /*  Tile tile = context.createRubyModel(Tile.class);
        Resource  resource = context.createRubyModel(Resource.class);
        ResourceType resourceType = context.createRubyModel(ResourceType.class);
        TerrainType terrainType  = context.createRubyModel(TerrainType.class);

        resourceType.setName("testResource");
        terrainType.setName("TestTerrain");
        resource.setResourceType(resourceType);
        tile.addResource(resource);
        tile.setTerrainType(terrainType);

        tile.setXoffset(0);
        tile.setYoffset(1);
        tile.setXposition(2);
        tile.setYposition(3);
        resourceType.Save("/resourcetypes");
        resource.Save("/resources/");
        terrainType.Save("/terraintypes");
        tile.Save("/tiles/"); */
        //context.SaveObject(resourceType,"")

       // MilitaryStronghold base = context.createRubyModel(MilitaryStronghold.class);
       // base.setHealth(1000); 
       // base.setName("The Third Castle!");
     //   base.Save("/militarystrongholds/");
	}
}
	

