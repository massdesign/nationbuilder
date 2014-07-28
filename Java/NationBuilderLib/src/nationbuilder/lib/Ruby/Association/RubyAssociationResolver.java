package nationbuilder.lib.Ruby.Association;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;

/**
 * Created by patrick on 7/23/14.
 */
public class RubyAssociationResolver {
       // TODO: someday we will implement this stuff..
    public RubyObjectHierarchy buildObjectTree(RubyModel model) {
        RubyObjectHierarchy result = new RubyObjectHierarchy();

        if (model != null) {
            // List<Field> privateFields = new ArrayList<Field>();

            Field[] allFields = model.getClass().getDeclaredFields();

            for (Field field : allFields) {
                // only do reflection on private fields
                if (Modifier.isPrivate(field.getModifiers())) {
                    // privateFields.add(field);

                }
            }


        }

        return  result;
    }

        // NOTE: workaround to create something that works for now
        public static int [] CreateIDsFromArrayList(List<? extends  RubyModel> list)
        {
            int [] result = new int[list.size()];
            int i=0;
            for(RubyModel model : list)
            {
                if(model.getId() != null)
                {
                  result[i] = Integer.parseInt(model.getId().getId());
                    i++;
                }
            }
            return result;
        }

}
