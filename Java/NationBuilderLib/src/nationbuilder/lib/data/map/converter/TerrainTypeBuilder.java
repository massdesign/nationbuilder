package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.data.map.entities.TerrainType;
import nationbuilder.lib.data.map.xml.Property;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by patrick on 5/19/16.
 */
public class TerrainTypeBuilder {


    private XmlTileBuilder parentBuilder;

    public TerrainTypeBuilder(XmlTileBuilder parentBuilder) {

        this.parentBuilder = parentBuilder;
    }




    public TerrainType convertPropertyToTerrainType(ArrayList<Property> properties,List<TerrainType> models)
    {
        TerrainType result = null;
        // search in the database for the right TerrainType..

        // TODO: pretty ineffecient way of quering the db, find a way to handle db queries in a generic way
        // SQL syntax for this bitch would be SELECT id from resourcetype WHERE name = 'name'
        for(Property property : properties)
        {
            if(property.getName().toLowerCase().equals("tiletype"))
            {
                // TODO: dit proberen op te lossen zonder dat we de rubycontext erbij in moeten tillen
               // List<TerrainType> models = this.rubyContext.getModels(TerrainType.class);
                for(TerrainType model : models)
                {
                    if(model.getName().equals(property.getValue()))
                    {
                        result = model;
                        break;
                    }
                }
            }
        }
        return result;
    }
}
