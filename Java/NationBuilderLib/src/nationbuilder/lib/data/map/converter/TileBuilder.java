package nationbuilder.lib.data.map.converter;

import java.util.ArrayList;
import nationbuilder.lib.Ruby.RubyContext;
import nationbuilder.lib.data.map.entities.Image;
import nationbuilder.lib.data.map.entities.Layer;
import nationbuilder.lib.data.map.entities.Tile;
import nationbuilder.lib.data.map.xml.XmlTile;

/**
 * @author patrick.ekkel
 */
public class TileBuilder
{
	private Layer layer;
	private RubyContext rubyContext;
	private ArrayList<Image> images;
	public TileBuilder(Layer  layer,RubyContext rubyContext,ArrayList<Image> images)  {
		this.layer = layer;
		this.rubyContext = rubyContext;
		this.images  = images;
	}


	public Tile createTile(int xposition,int yposition,XmlTile xmlTile) {

		Tile result =   this.rubyContext.createRubyModel(Tile.class);
		int GID = xmlTile.getGID();
		result.setXposition(xposition);
		result.setYposition(yposition);
		result.setGidtag(GID);
		result.setLayer(this.layer);

		Image tileImage = this.getTileImage(GID);
		result.setImage(tileImage);


		int offset = result.getImage().getFirstGid();
		int columns = result.getImage().getWidth() / result.getImage().getTileWidth();
		int rows = result.getImage().getHeight() / result.getImage().getTileHeight();
		int gid_counter = offset;
		// first
		int currentrow = 1;
		int currentcolumn = 1;

		while (gid_counter < (GID))
		{
			gid_counter++;
			if (currentcolumn == columns)
			{
				currentcolumn = 1;
				currentrow++;
			}
			else
			{
				currentcolumn++;
			}

		}
		result.setXoffset(currentcolumn - 1);
		result.setYoffset(currentrow - 1);
		return result;
	}



	private Image getTileImage(int gid)
	{
		Image result = null;
		for (int x = 0; x <  this.images.size(); x++)
		{

			if (gid >= this.images.get(x).getFirstGid())
			{
				if ((x + 1) != this.images.size())
				{
					int lastGid = this.images.get(x + 1).getFirstGid();
					// do minus -1 because otherwise we select the first element of the next tileset
					if (gid <= (lastGid - 1))
					{
						//newtile.setImage(this.images.get(x));
						result = this.images.get(x);
						break;
					}
				}
				else
				{
					result  =  this.images.get(x);
					break;
				}
			}
		}
		return result;
	}

}
