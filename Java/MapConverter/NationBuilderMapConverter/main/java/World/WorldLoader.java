package World;

import nationbuilder.lib.Ruby.Exceptions.ObjectConversionFailedException;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.converter.TiledMapConverter;
import nationbuilder.lib.data.map.entities.MapDataset;
import nationbuilder.lib.data.map.mapservice.MapServiceConnector;
import nationbuilder.lib.data.map.xml.Configuration;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.TiledXmlMapFactory;

/**
 * Created by patrick on 11/2/14.
 */
public class WorldLoader {


    RubyContext context;
    PreFiller preFiller;
    PostFiller postFiller;

    MapDataset mapDataset;

    public WorldLoader(RubyContext context)
    {
        this.context = context;
        this.preFiller = new PreFiller(context);
        this.postFiller = new PostFiller(context);

    }

    public void PreFiller() throws ObjectConversionFailedException {

        this.preFiller.Fill();
    }
    public void ConvertMap()
    {
        TiledXmlMapFactory tiledXmlMapFactory = new TiledXmlMapFactory();
        TiledXmlMap tiledXmlMap = tiledXmlMapFactory.createTiledXmlMap(Configuration.NB13Map);
        TiledMapConverter converter = new TiledMapConverter(tiledXmlMap,context);
        converter.Convert();
        this.mapDataset = converter.GetMapDataset();

        PostMapFiller postMapFiller = new PostMapFiller(context);
        postMapFiller.setMapDataset(this.mapDataset);
        postMapFiller.Fill();
    }
    public void PostFiller() throws ObjectConversionFailedException {
        this.postFiller.setMapDataset(mapDataset);
        this.postFiller.Fill();
    }

    public void Run() throws RubyException
    {
        //this.PreFiller();
        this.ConvertMap();
       // this.PostFiller();
        this.context.commit();
    }
    public void TestRun()
    {
        this.postFiller.testFill();

    }


}
