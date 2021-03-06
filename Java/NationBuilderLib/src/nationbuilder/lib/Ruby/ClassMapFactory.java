package nationbuilder.lib.Ruby;

import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.collections.DoubleLinkedList;

/**
 * Created by patrick on 2/26/16.
 */
public class ClassMapFactory {


    private Class subjectClazz;

    public ClassMapFactory(Class clazz) {
        this.subjectClazz = clazz;
    }


    public ClassMap createClassmap(RubyModel instance) {

        DoubleLinkedList<Class> linkedList = new DoubleLinkedList<>();

        // the root to start with
        Class currentClass = subjectClazz;

        while(currentClass != null) {
            linkedList.add(currentClass);
            currentClass = currentClass.getSuperclass();
        }

        ClassMap cm = new ClassMap(linkedList);
        cm.setCurrentObjectInstance(instance);
        return cm;
    }





}
