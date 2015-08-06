
import java.io.IOException;
import World.WorldLoader;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.Ruby.RubyContextFactory;
import nationbuilder.lib.Ruby.RubyContextType;
import nationbuilder.lib.data.map.entities.Resource;
import nationbuilder.lib.data.map.entities.ResourceType;
import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.enums.RESOURCELOCATION;

public class Main {

    public static void main(String[] args) throws IOException, RubyException {
        RubyContext context = new RubyContextFactory().createRubyContext(RubyContextType.BULK_INSERT_SQL_JSON_UPDATE_DELETE_SELECT);
       // WorldLoader worldLoader = new WorldLoader(context);
       // worldLoader.Run();
        String resourceTypeUrl = "/resourcetypes";
        String resourceUrl = "/resources";

        Resource resource = context.createRubyModel(Resource.class);
        ResourceType resourceType = context.createRubyModel(ResourceType.class);
        TerrainType terrainType = context.createRubyModel(TerrainType.class);
        Tile tile = context.createRubyModel(Tile.class);


        terrainType.setName("Testterraintype");
        resourceType.setName("testresourcetype");
        resourceType.setRegenerating(true);
        resourceType.setLocation(RESOURCELOCATION.EMBEDDEDROCK);

        tile.setXoffset(3);
        tile.setYoffset(4);

        tile.setXposition(34);
        tile.setYposition(90);

        resource.setResourceType(resourceType);
        tile.addResource(resource);
        tile.setTerrainType(terrainType);
        resourceType.Save("/resourcetypes");
        resource.Save("/resources/");
        terrainType.Save("/terraintypes");
        tile.Save("/tiles/");


        context.commit();
    }

}



