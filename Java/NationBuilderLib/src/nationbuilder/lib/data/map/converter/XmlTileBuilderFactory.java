package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.data.map.xml.TileSet;

import java.util.ArrayList;

/**
 * Created by patrick on 5/19/16.
 */
public class XmlTileBuilderFactory {

    private ArrayList<TileSet> tileSets;
    public XmlTileBuilderFactory(ArrayList<TileSet> tileSets) {

        this.tileSets = tileSets;
    }


    public XmlTileBuilder createXmlTileBuilder() {

        XmlTileBuilder result = new XmlTileBuilder();



        return result;
    }
}
