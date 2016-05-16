package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.data.map.xml.Property;
import nationbuilder.lib.data.map.xml.XmlTile;

import java.util.HashMap;

/**
 * Created by patrick on 5/15/16.
 */
public class XmlTileFactory {




    public boolean isTile(XmlTile tile) {

       return hasProperty("tiletype",tile);

    }
    public boolean isCityItem(XmlTile tile) {

        return hasProperty("city_type",tile);
    }

    public boolean isPowerGridItem(XmlTile tile) {

        return hasProperty("powerstation_type",tile);
    }

    private boolean hasProperty(String propertyName ,XmlTile tile) {
        boolean result = false;
        for(Property xmlProperty : tile.getProperties()) {

            if(xmlProperty.getName().toLowerCase().equals(propertyName)) {

                result = true;
                break;
            }

        }
        return result;
    }


}
