package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * Created by patrick on 5/11/14.
 */
@Entity(tableName = "maps")
public class MapMap extends BaseRubyModel {

    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;

    public int getTileWidth() {
        return tileWidth;
    }

    public void setTileWidth(int tileWidth) {
        this.tileWidth = tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }

    public void setTileHeight(int tileHeight) {
        this.tileHeight = tileHeight;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public MapMap()
    {

    }
}
