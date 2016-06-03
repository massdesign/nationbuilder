package nationbuilder.lib.data.map.converter;

import nationbuilder.lib.data.map.converter.converterobjects.ProcessedImage;
import nationbuilder.lib.data.map.converter.converterobjects.ProcessedTileset;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.entities.MapImageFile;
import nationbuilder.lib.data.map.xml.XmlImage;
import nationbuilder.lib.data.map.xml.XmlTileset;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by patrick on 5/19/16.
 */
public class XmlImageBuilder
{

    private  ArrayList<Image> mapImages;
    private ConverterContext converterContext;

    public XmlImageBuilder(ConverterContext converterContext) {
        this.mapImages = new ArrayList<>();

        this.converterContext = converterContext;
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

    public void build(XmlTileset xmlTileset,ProcessedTileset processedTileset) {

       XmlImage xmlImage =   xmlTileset.getImage();
       ProcessedImage processedImage = new ProcessedImage();

        processedImage.setSource(xmlImage.getFileLocation());
        processedImage.setHeight(xmlImage.getHeight());
        processedImage.setWidth(xmlImage.getWidth());


        processedTileset.


    }

    private ArrayList<Image> convertTilesets(ArrayList<XmlTileset> tilesets,Image mapImage,MapImageFile mapImageFile)
    {
        ArrayList<Image> mapImages = new ArrayList<Image>();
        for(XmlTileset tileset : tilesets)
        {
            // TODO: dit probleem oplossen
           // this.parentBuilder.build(tileset);
            //addTilesToTerrainTypes(tileset);
            XmlImage image = tileset.getImage();

           // Image mapImage = rubyContext.createRubyModel(Image.class);
            //MapImageFile mapImageFile = rubyContext.createRubyModel(MapImageFile.class);
            mapImage.setMap(this.converterContext.getMap());
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
