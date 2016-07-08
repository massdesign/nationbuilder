package nationbuilder.lib.Ruby.services;

import nationbuilder.lib.data.map.mapservice.TiledPropertyManager;

/**
 * @author patrick.ekkel
 */
public class PropertyManagerService implements RubyDataService
{
	private TiledPropertyManager tiledPropertyManager;

	public TiledPropertyManager getTiledPropertyManager()
	{
		return tiledPropertyManager;
	}

	public void setTiledPropertyManager(TiledPropertyManager tiledPropertyManager)
	{
		this.tiledPropertyManager = tiledPropertyManager;
	}
}
