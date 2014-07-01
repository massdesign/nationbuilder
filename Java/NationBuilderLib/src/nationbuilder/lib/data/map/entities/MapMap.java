package nationbuilder.lib.data.map.entities;

import nationbuilder.lib.http.data.ID;

/**
 * Created by patrick on 5/11/14.
 */
public class MapMap {

    private int tileWidth;
    private int tileHeight;
    private int width;
    private int height;

    private ID id;

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

    public ID getId() {
        return id;
    }

    public void setId(ID id) {
        this.id = id;
    }


    public MapMap()
    {

    }
}
