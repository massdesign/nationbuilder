package nationbuilder.lib.sql.connectors;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import nationbuilder.lib.Ruby.Association.annotation.Entity;
import nationbuilder.lib.Ruby.Association.annotation.MappingInfo;
import nationbuilder.lib.Ruby.Exceptions.*;
import nationbuilder.lib.Ruby.Interfaces.RubyCreateService;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.Ruby.RelationResolveService;
import nationbuilder.lib.Ruby.RelationScanService;
import nationbuilder.lib.Ruby.orm.ReferenceMapping;
import nationbuilder.lib.Ruby.configuration.RubyConfiguration;
import nationbuilder.lib.Ruby.services.RubyDataServiceAccessor;
import nationbuilder.lib.connectors.SqlObjectBuilder;
import nationbuilder.lib.data.map.ResponseData;
import nationbuilder.lib.sql.SqlQueryManager;
import nationbuilder.lib.sql.SqlResponseData;

/**
 * Created by patrick on 12/16/14.
 */
public class BulkSqlCreateServiceConnector implements RubyCreateService
{
	SqlQueryManager sqlQueryManager;
	SqlObjectBuilder objectBuilder;
	HashMap<RubyModel,String> persistedObjects;

//	SqlObjectToRowConverter sqlObjectToRowConverter;
	public BulkSqlCreateServiceConnector(SqlObjectBuilder objectBuilder)
	{
		this.sqlQueryManager = new SqlQueryManager(RubyConfiguration.mySqlUsername,RubyConfiguration.mySqlPassword,RubyConfiguration.mySqlServer,RubyConfiguration.mySqlDatabase,RubyConfiguration.mySqlTempDir);
		this.objectBuilder = objectBuilder;
		this.persistedObjects = new HashMap<>();

	}

	@Override
	public ResponseData postObject(Object objectToPost, String resourceUrl, String rootValue) throws IOException
	{
		return null;
	}
	// TODO: RubyModel als parameter toevoegen, nu is alles Object dit kan zorgen voor bugs
	@Override
	public ResponseData postObject(Object objectToPost, String resourceUrl) throws ObjectPersistanceFailedException, ObjectConversionFailedException, MissingAnnotationException {
        SqlResponseData responseData = new SqlResponseData();
		String sql = this.objectBuilder.createStringFromObject(objectToPost);
        this.persistedObjects.put((RubyModel)objectToPost,sql);
        responseData.setSql(sql);
		return responseData;
	}



    /*public List<MappingInfo> scanPersistedObjectsForRelations()
    {
        // TODO: tamelijk lompe manier om alle relaties in kaart te brengen. Bij grote datasets gaat dit misschien een bottleneck worden
        Iterator poit = this.persistedObjects.entrySet().iterator();

        List<MappingInfo> mappingList = new ArrayList<>();
        while(poit.hasNext()) {
            Map.Entry pair = (Map.Entry)poit.next();
            RubyModel model =(RubyModel)pair.getKey();

           Field[] fields =   model.getClass().getDeclaredFields();

            for(Field field : fields)
            {
               if(field != null)
               {
                   if(field.getAnnotations().length > 0)
                   {
                       field.setAccessible(true);
                       MappingInfo mappingInfo = RubyAssociationResolver.getMappingInfo(field,model);
                       if(mappingInfo != null && mappingInfo.isForeignRelation())
                       {
                           mappingList.add(mappingInfo);
                       }
                   }
               }
            }
        }
        return mappingList;
    }*/

    /*public void resolveForeignKeys(List<MappingInfo> mappingList) {


        for(MappingInfo mappingInfo : mappingList)
        {
            if(mappingInfo.getField() != null)
            {
                try
                {
                   RubyModel objectToReference =  mappingInfo.getInstance();

                   Object foreignKeyHolderField = mappingInfo.getField().get(mappingInfo.getInstance());
                    if(foreignKeyHolderField instanceof RubyModel)
                    {
                       // resolveOneToOneForeignKeys(mappingInfo,objectToReference);
                    }
                    // NOTE: ik weet niet of list een goeie manier is om onderscheid te maken tussen een one to one of een one to many, maar voor nu werkt dit aardig
                    else if(foreignKeyHolderField instanceof  List) {
                        //resovleOneToManyForeignKeys(mappingInfo,objectToReference);
                    }
                }
                catch (IllegalAccessException e)
                {
                    e.printStackTrace();
                }

            }
        }

    }*/
    // TODO: hier in de toekomst een strategy pattern voor implementeren, als we dit gaan refactoren

    /*
    private void resolveOneToOneForeignKeys(MappingInfo mappingInfo,RubyModel objectToReference) throws IllegalAccessException, NoSuchFieldException
    {

        RubyModel foreignKeyHolder = (RubyModel) mappingInfo.getField().get(mappingInfo.getInstance());
        Field objectReferenceField = mappingInfo.getMappedByClazz().getDeclaredField(mappingInfo.getMappedBy());
        objectReferenceField.setAccessible(true);
        objectReferenceField
         .set(foreignKeyHolder, new ReferenceMapping(objectToReference.getId(), objectToReference.getClass()));
    }*/
    /*
    private void resovleOneToManyForeignKeys(MappingInfo mappingInfo, RubyModel objectToReference) throws IllegalAccessException, NoSuchFieldException
    {

        List list =  (List)mappingInfo.getField().get(mappingInfo.getInstance());

        Field objectReferenceField = mappingInfo.getMappedByClazz().getDeclaredField(mappingInfo.getMappedBy());
        objectReferenceField.setAccessible(true);
        for(Object foreignKeyHolder : list) {

            objectReferenceField.set(foreignKeyHolder, new ReferenceMapping(objectToReference.getId(), objectToReference.getClass()));
        }

    }*/
    public String resolveUnresolvedFields(RubyModel key,String value)
    {
        String resolvedSql = "";

        Pattern p = Pattern.compile(".bui.([_a-z-A-Z]*).eui");
        Matcher m  = p.matcher(value);
      //  if(m.matches()) {
            while (m.find()) {

                String field_id = m.group(1);
                String stripped_field_id = field_id.replace("_id","");
                try {
                    // expecting this to be ReferenceMapping if not well we get a cast exception
                      Field refMappingField = key.getClass().getDeclaredField(stripped_field_id); //.get(key);
                      refMappingField.setAccessible(true);
                      Object refMapping = refMappingField.get(key);
                      if(refMapping != null)
                      {
                          ReferenceMapping rm =  (ReferenceMapping)refMapping;
                          resolvedSql = value.replace("<bui>" + field_id +"<eui>",rm.getID().getId());
                      }
                      else
                      {
                          resolvedSql = value.replace("<bui>" + field_id + "<eui>","0");
                      }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
       // }
        return resolvedSql;

    }
    private boolean hasUnresolvedfields(String sqlString)
    {
        boolean result = false;

           // TODO: char sequence in een variable zetten
           if(sqlString.contains("<bui>")) {
               result = true;
           }
        return result;
    }

    @Override
    public void commit() throws RubyException {

        Iterator poit = this.persistedObjects.entrySet().iterator();
        HashMap<String,List<String>> rows = new HashMap<>();
        // TODO: call the resolveRelationService here.
        RelationResolveService relationResolveService = RubyDataServiceAccessor.getInstance().getService(RelationResolveService.class);
        RelationScanService relationScanService = RubyDataServiceAccessor.getInstance().getService(RelationScanService.class);
        relationResolveService.resolveForeignKeys(relationScanService.scanForRelations(poit));

        //resolveForeignKeys(scanPersistedObjectsForRelations());

        while(poit.hasNext())
        {
            Map.Entry pair = (Map.Entry)poit.next();
            RubyModel model = (RubyModel)pair.getKey();
            Entity entity =  model.getClass().getAnnotation(Entity.class);
            String sqlString = (String)pair.getValue();
            if(hasUnresolvedfields(sqlString))
            {
              sqlString =  resolveUnresolvedFields(model,sqlString);
              pair.setValue(sqlString);
            }

            if(rows.containsKey(entity.tableName()))
            {
                rows.get(entity.tableName()).add(sqlString);
            }
            else
            {
                List<String> newList = new ArrayList<>();
                newList.add(sqlString);
                rows.put(entity.tableName(),newList);
            }
            model.setCommitted(true);
        }


        try {
            if(rows.size() > 0) {
                Iterator rit = rows.entrySet().iterator();
                while(rit.hasNext())
                {
                    Map.Entry pair = (Map.Entry)rit.next();
                    List<String> tablerows = (List<String>)pair.getValue();
                    this.sqlQueryManager.executeBulkInsert(tablerows,(String)pair.getKey());
                }

            }
        } catch (SQLException e) {
            throw new BulkInsertFailedException(e.getMessage());
        }

    }
}
