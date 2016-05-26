package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.entities.MapImageFile;
import nationbuilder.lib.data.map.xml.TileSet;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by patrick on 5/19/16.
 */
public class ImageBuilder {

    private  ArrayList<Image> mapImages;
    private TilesetBuilder parentBuilder;

    public ImageBuilder(TilesetBuilder parentBuilder) {
        this.mapImages = new ArrayList<>();

        this.parentBuilder = parentBuilder;
    }


    public Image getTileImage(int gid)
    {
        Image result = null;
        for(int x=0;x<mapImages.size();x++)
        {
            if(gid >= this.mapImages.get(x).getFirstGid())
            {
                if((x+1) != mapImages.size())
                {
                    int lastGid = this.mapImages.get(x+1).getFirstGid();
                    // do minus -1 because otherwise we select the first element of the next tileset
                    if(gid <= (lastGid-1))
                    {
                        result = this.mapImages.get(x);
                      //  newtile.setImage(this.mapImages.get(x));
                        break;
                    }
                }
                else
                {
                    result = this.mapImages.get(x);
                   // newtile.setImage(this.mapImages.get(x));
                    break;
                }
            }
        }

        return result;
    }
    private ArrayList<Image> convertTilesets(ArrayList<TileSet> tilesets,Image mapImage,MapImageFile mapImageFile)
    {
        ArrayList<Image> mapImages = new ArrayList<Image>();
        for(TileSet tileset : tilesets)
        {
            this.parentBuilder.addTileset(tileset);
            //addTilesToTerrainTypes(tileset);
            nationbuilder.lib.data.map.xml.Image image =	tileset.getImage();

           // Image mapImage = rubyContext.createRubyModel(Image.class);
            //MapImageFile mapImageFile = rubyContext.createRubyModel(MapImageFile.class);
            mapImage.setMap(this.map);
            mapImageFile.setResource(new File(image.getFileLocation()));
            mapImage.setImageFile(mapImageFile);
            mapImage.setHeight(image.getHeight());
            mapImage.setWidth(image.getWidth());

            mapImage.setTileHeight(tileset.getTileHeight());
            mapImage.setTileWidth(tileset.getTileWidth());
            mapImage.setFirstGid(tileset.getFirstGid());
            mapImage.setLastGid(tileset.getLastGid());

            mapImage.setUrl("/upload/" + image.getName());
            mapImage.setName(image.getName());

            mapImages.add(mapImage);
        }

        return mapImages;

    }
}
