package nationbuilder.lib.Ruby;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import nationbuilder.lib.Ruby.Association.annotation.OneToOne;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;

public class RubyReferenceScanner {

	
	RubyStore rubyStore;
	
	
	private HashMap<ID,HashMap<Class,Field>> referenceMappings;
	private HashMap<ID,HashMap<Class,Field>> modelMappings;
	
	
	public RubyReferenceScanner(RubyStore rubyStore) 
	{
		this.rubyStore = rubyStore;
		this.referenceMappings =  new HashMap<>();
		this.modelMappings = new HashMap<>();
	}
	
	
	public void scanAndApplyReferences()
	{
		Iterator it = this.rubyStore.getIterator();
		
		while(it.hasNext())
		{
		       Map.Entry pair = (Map.Entry)it.next();
		       
		       RubyModel value = (RubyModel)pair.getValue();		       
		       HashMap<Class,Field> referenceFields = this.getReferenceFieldsToMap(value);
		       HashMap<Class,Field> modelFields = this.getModelFieldsToMap(value);
		       if(referenceFields.size() > 0)
		       {
		    	   this.referenceMappings.put(value.getId(),referenceFields);
		       }
		       if(modelFields.size() > 0)
		       {
		    	   this.modelMappings.put(value.getId(),modelFields);
		       }
		       
		}
		
	}
	// TODO: we could combine these two methods into one.. possible improvement for the future
	private HashMap<Class,Field> getModelFieldsToMap(RubyModel model)
	{
		HashMap<Class,Field> result = new HashMap<Class,Field>();
		
		
		Class currentClass = model.getClass();
		while(currentClass != null )
		{
			for(Field field :  currentClass.getDeclaredFields())
			{
				
				if(field.getAnnotations().length > 0)
				{
					// TODO: only onetone is implemented, implement the rest if needed
					OneToOne otoAnnotation = field.getAnnotation(OneToOne.class);
					
					if(otoAnnotation != null)
					{
						
						if(!otoAnnotation.mappedBy().equals(""))
						{
							result.put(currentClass, field);
						}
						
					}
				}
				currentClass = currentClass.getSuperclass();
				
			}
		}
		return result;
		
	}
	private HashMap<Class,Field> getReferenceFieldsToMap(RubyModel model) 
	{
		HashMap<Class,Field> result = new HashMap<Class,Field>();
				
		Class currentClass = model.getClass();
		while(currentClass != null )
		{
			for(Field field :  currentClass.getDeclaredFields())
			{
					
				if(field.getType().getSimpleName().toLowerCase().equals("referencemapping"))
				{
					result.put(currentClass, field);
				}
				
			}
			currentClass = currentClass.getSuperclass();
		}
		
		return result;			
	}
	
	
	
}
