package nationbuilder.lib.data.map.entities;

import java.util.ArrayList;
import java.util.List;
import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.InhiritanceStrategy;
import nationbuilder.lib.Ruby.Association.annotation.OneToMany;
import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Association.annotation.Transient;

/**
 * @author patrick.ekkel
 */
@Entity(tableName = "power_grid_nodes",strategy = InhiritanceStrategy.TablePerClass)
public class PowerGridNode extends Node
{
	//private int [] pcids;

	// TODO: recursieve structuren zijn een probleem in RubyDI. Als eerste omdat er dan geen einde komt aan de objectstructuur.
	// Dit zouden we kunnen voorkomen door te detecteren welke objecten we al aangeraakt hebben.. om te voorkomen dat we de hele datastructuur moeten gaan bijhouden zouden we een annotatie kunnen toevoegen
	// die er markeert dat dit een cyclische datastructuur is en daarom bijgehouden moet worden. verder zou de @cyclic annotatie er ook voor moeten zorgen dat het object vroegtijdig gepersisteerd wordt zodat we geen problemen
	// krijgen met andere afhankelijke datastructuren

	// Probleem met opslaan van deze datastructuur is als volgt/ recursiviteit powergridnode->powerconnections(1)->A->powergridnode->powerconnections(1)->A
	//@OneToMany(mapIdTo = "pcids",mappedBy = "powerconnection", mappedByClazz = PowerConnection.class)
	//List<PowerConnection> powerConnections = new ArrayList<>();


//	public void  addConnection(PowerConnection powerConnection) {

//		this.powerConnections.add(powerConnection);
//	}

}
