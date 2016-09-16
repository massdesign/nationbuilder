package nationbuilder.lib.data.map.entities;

/**
 * Tagging interface voor alles wat met het powergrid te maken heeft
 * @author patrick.ekkel
 */
public interface PowerGridComponent extends NamedObject
{
	void setPowerGridNode(PowerGridNode powerGridNode);
	PowerGridNode getPowergridNode();
	void addConnection(PowerConnection powerConnection);
}
