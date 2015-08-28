package nationbuilder.lib.Ruby;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.annotation.MappingInfo;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.services.RubyDataService;

/**
 * @author patrick.ekkel
 */
public class RelationScanService implements RubyDataService
{

	public List<MappingInfo> scanForRelations(Iterator poit) {

		List<MappingInfo> mappingList = new ArrayList<>();
		while (poit.hasNext())
		{
			Map.Entry pair = (Map.Entry) poit.next();
			RubyModel model = (RubyModel) pair.getKey();

			Field[] fields = model.getClass().getDeclaredFields();

			for (Field field : fields)
			{
				if (field != null)
				{
					if (field.getAnnotations().length > 0)
					{
						field.setAccessible(true);
						MappingInfo mappingInfo = RubyAssociationResolver.getMappingInfo(field, model);
						if (mappingInfo != null && mappingInfo.isForeignRelation())
						{
							mappingList.add(mappingInfo);
						}
					}
				}
			}
		}
		return mappingList;

	}
}
