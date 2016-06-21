package nationbuilder.lib.Ruby.services;

import java.lang.reflect.Field;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;

/**
 * @author patrick.ekkel
 */
public class ResolveUnresolvedFieldsService implements RubyDataService
{
	public boolean hasUnresolvedfields(String sqlString)
	{
		boolean result = false;

		// TODO: char sequence in een variable zetten
		if (sqlString.contains("<bui>"))
		{
			result = true;
		}
		return result;
	}
	public String resolve(Class clazz, RubyModel key, String value)
	{
		String resolvedSql = "";

		Pattern p = Pattern.compile(".bui.([_a-z-A-Z-0-9]*).eui");
		Matcher m = p.matcher(value);
		Field refMappingField = null;
		//  if(m.matches()) {
		while (m.find())
		{

			String field_id = m.group(1);
			try
			{
				// expecting this to be ReferenceMapping if not well we get a cast exception

				refMappingField = RubyAssociationResolver.getRefMappingField(clazz, field_id);

				refMappingField.setAccessible(true);
				Object refMapping = refMappingField.get(key);
				if (refMapping != null)
				{
					ReferenceMapping rm = (ReferenceMapping) refMapping;
					resolvedSql = value.replace("<bui>" + field_id + "<eui>", rm.getID().getId());
				}
				else
				{
					resolvedSql = value.replace("<bui>" + field_id + "<eui>", "0");
				}

			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
		}
		// }
		return resolvedSql;

	}
}
