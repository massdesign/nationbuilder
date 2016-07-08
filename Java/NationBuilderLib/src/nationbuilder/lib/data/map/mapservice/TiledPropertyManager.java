package nationbuilder.lib.data.map.mapservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nationbuilder.lib.data.map.xml.Property;
import nationbuilder.lib.data.map.xml.XmlTileDefinition;
import nationbuilder.lib.data.map.xml.XmlTileSet;

/**
 * @author patrick.ekkel
 */
public class TiledPropertyManager
{
	private List<XmlTileSet> tileSets;
	boolean indexed = false;

	private HashMap<Integer, List<IndexedProperty>> indexedPropertyHashMap;
	public TiledPropertyManager(List<XmlTileSet> tileSets) {

		this.tileSets = tileSets;
		this.indexedPropertyHashMap =  new HashMap<>();
	}


	public TileProperty getTileProperty(TilePropertyType type,int gid) {

		TileProperty result =  null;
		if(indexed) {

		  List<IndexedProperty>  indexedProperties  =	this.indexedPropertyHashMap.get(gid);

			for(IndexedProperty  indexedProperty : indexedProperties)  {

				if(indexedProperty.getName().equals(type.xmlName)) {

					result = new TileProperty(type,indexedProperty.getValue());
					break;
				}

			}

		}
		else {
			// depth first search indexing van alle tile properties
			for (XmlTileSet tileSet : tileSets)
			{

				List<XmlTileDefinition> tileDefinitions = tileSet.getTileDefinitions();
			   for(XmlTileDefinition tileDefinition :	tileDefinitions) {
					List<Property> properties = tileDefinition.getProperties();
				   for(Property property : properties ) {
						// first check if  there is  an  entry
					   int calculatedDefinitionId = tileSet.getFirstGid() + tileDefinition.getId();
					   IndexedProperty newProperty = new IndexedProperty(property.getName(),property.getValue());
					   if(indexedPropertyHashMap.containsKey(calculatedDefinitionId))
					   {
						   // if  it  already exists.. get all  the properties and  check if that property is already added
						   List<IndexedProperty> indexedProperties = indexedPropertyHashMap.get(calculatedDefinitionId);
						   indexedProperties.add(newProperty);
						   indexedPropertyHashMap.remove(calculatedDefinitionId);
						   indexedPropertyHashMap.put(calculatedDefinitionId,indexedProperties);
					   }
					   else {
							List<IndexedProperty> indexedProperties  = new ArrayList<>();
						   indexedProperties.add(newProperty);
						   indexedPropertyHashMap.put(calculatedDefinitionId, indexedProperties);
					   }

					   if (newProperty.getName().equals(type.xmlName) && calculatedDefinitionId == (gid+tileSet.getFirstGid()))
					   {
						   result = new TileProperty(type, newProperty.getValue());
					   }

				   }

			   }


			}

			this.indexed = true;
		}

		return result;
	}


}
