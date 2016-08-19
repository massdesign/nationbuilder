package nationbuilder.lib.data.map.mapservice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import nationbuilder.lib.data.map.exceptions.MapConvertException;
import nationbuilder.lib.data.map.xml.Property;
import nationbuilder.lib.data.map.xml.TiledXmlMap;
import nationbuilder.lib.data.map.xml.XmlObject;
import nationbuilder.lib.data.map.xml.XmlObjectGroup;
import nationbuilder.lib.data.map.xml.XmlTileDefinition;
import nationbuilder.lib.data.map.xml.XmlTileSet;

/**
 * @author patrick.ekkel
 */
public class TiledPropertyManager
{
	private List<XmlTileSet> tileSets;
	private TiledXmlMap tiledXmlMap;
	boolean indexed = false;

	private HashMap<Integer, List<IndexedProperty>> indexedPropertyHashMap;
	public TiledPropertyManager(List<XmlTileSet> tileSets,TiledXmlMap tiledXmlMap) {

		this.tileSets = tileSets;
		this.tiledXmlMap = tiledXmlMap;
		this.indexedPropertyHashMap =  new HashMap<>();
	}


	public List<TiledXmlProperty> getObjectGroupProperties(int xposition,int yposition) throws MapConvertException
	{
		// TODO: dit kunnen we uit de map halen
		int tileWidth  = 32;
		int tileHeight = 32;
		xposition *= tileWidth;
		yposition *= tileHeight;
		List<TiledXmlProperty> result = new ArrayList<>();


		List<XmlObjectGroup> objectGroups = this.tiledXmlMap.getObjectGroups();

		for(XmlObjectGroup objectGroup : objectGroups) {


				 List<XmlObject> xmlObjects = objectGroup.getObjects();
			for(XmlObject xmlObject : xmlObjects) {

				float x1 = xmlObject.getX();
				float x2 = xmlObject.getX() + Float.valueOf(xmlObject.width());
				float y1 = xmlObject.getY();
				float y2 = xmlObject.getY() + Float.valueOf(xmlObject.height());
				if((xposition > x1 && xposition < x2) && (yposition > y1  && yposition < y2))
				{
					for (Property property : xmlObject.getProperties())
					{

						result.add(new TileProperty(TilePropertyType.convertToEnum(property.getName()), property.getValue()));
					}
				}

			}
		}
		return result;
	}

	public TiledXmlProperty getTileProperty(TilePropertyType type,int gid) {

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
