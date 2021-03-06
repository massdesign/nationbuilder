package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Column;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;

/**
 * Created by patrick on 5/11/14.
 */
@Entity(tableName = "maps")
public class MapMap extends BaseRubyModel {

    @Column
    private int tileWidth;
    @Column
    private int tileHeight;
    @Column
    private int width;
    @Column
    private int height;
    private int [] lids;
    private int [] mids;
    @Column(setMethod = "addLayer")
    @OneToMany(mapIdTo = "lids", mappedBy = "map", mappedByClazz = Layer.class)
    private List<Layer> layers = new ArrayList<>();
    @Column(setMethod = "addImage")
    @OneToMany(mapIdTo = "mids", mappedBy = "map", mappedByClazz = Image.class)
    private List<Image> images = new ArrayList<>();
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

    public void addImage(Image image) {
        this.images.add(image);
    }

    public void addLayer(Layer layer) {
        this.layers.add(layer);
    }

    public List<Layer> getLayers()
    {
        return layers;
    }
    public List<Image> getImages() {
        return images;
    }

}
