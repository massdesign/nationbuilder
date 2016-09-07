package nationbuilder.lib.Ruby;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import nationbuilder.lib.Logging.Log;
import nationbuilder.lib.Ruby.Association.RubyAssociationResolver;
import nationbuilder.lib.Ruby.Association.MappingInfo;
import nationbuilder.lib.Ruby.Exceptions.RubyException;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.orm.BaseRubyModel;
import nationbuilder.lib.Ruby.orm.RubyObjectKey;
import nationbuilder.lib.Ruby.services.RubyDataService;

/**
 * @author patrick.ekkel
 */
public class RelationScanService implements RubyDataService
{

	public RelationScanService() {

	}

	public List<MappingInfo> scanForRelations(Iterator poit) throws RubyException
	{

		List<MappingInfo> mappingList = new ArrayList<>();
		while (poit.hasNext())
		{
			Map.Entry pair = (Map.Entry) poit.next();
			RubyObjectKey objectKey = (RubyObjectKey) pair.getKey();

			RubyModel model = objectKey.getObject();
			Log.writeDebug("scan model " + model.toString() + " for relations");
			// TODO: superclass ook meenemen in het scan for relations systeem
			Class currentClass = model.getClass();
			while (currentClass != null)
			{
				Field[] fields = currentClass.getDeclaredFields();

				for (Field field : fields)
				{
					if (field != null)
					{
						if (field.getAnnotations().length > 0)
						{
							field.setAccessible(true);
							MappingInfo mappingInfo = null;
							try
							{
								mappingInfo = RubyAssociationResolver.getMappingInfo(field, model);
							}
							catch (NoSuchFieldException e)
							{

								throw new RubyException("Missing field " + field.getName());
							}
							if (mappingInfo != null && mappingInfo.isForeignRelation())
							{
								if(!mappingList.contains(mappingInfo))
								{
									mappingList.add(mappingInfo);
								}
							}
						}
					}
				}
				// We zijn niet geintereseerd in de properties van het  BaseRubyModel dus daar stoppen we met propageren
				currentClass = currentClass.getSuperclass();
				if(currentClass.equals(BaseRubyModel.class)) {
					currentClass = null;
				}
			}
		}
		return mappingList;

	}
}
