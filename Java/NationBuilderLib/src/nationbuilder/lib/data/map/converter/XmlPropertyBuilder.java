package nationbuilder.lib.data.map.converter;

import java.util.List;
import nationbuilder.lib.data.map.converter.converterobjects.ProcessedProperty;
import nationbuilder.lib.data.map.converter.converterobjects.ProcessedTileDefinition;
import nationbuilder.lib.data.map.converter.converterobjects.PropertyList;
import nationbuilder.lib.data.map.converter.converterobjects.PropertyList;
import nationbuilder.lib.data.map.xml.XmlProperty;

/**
 * @author patrick.ekkel
 */
public class XmlPropertyBuilder
{
	private ConverterContext converterContext;
	public XmlPropertyBuilder(ConverterContext converterContext) {

		this.converterContext = converterContext;
	}

	public void build(PropertyList propertyList,ProcessedTileDefinition processedTileDefinition) {


	  List<XmlProperty> properties = propertyList.getProperties();

		for(XmlProperty property : properties) {
		 ProcessedProperty newProperty =  new ProcessedProperty();
		 newProperty.setName(property.getName());
		 newProperty.setValue(property.getValue());
			processedTileDefinition.addProperty(newProperty);
		}


	}
}
