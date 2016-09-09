package nationbuilder.lib.data.map.entities;

/**
 * Tagging interface voor alles wat met het powergrid te maken heeft
 * @author patrick.ekkel
 */
public interface PowerGridComponent extends NamedObject
{
	public void setPowerGridNode(PowerGridNode powerGridNode);
}
