package nationbuilder.lib.Ruby;

import nationbuilder.lib.collections.DoubleLinkedList;

import java.util.List;

/**
 * Created by patrick on 2/26/16.
 */
public class ClassMap {

    private DoubleLinkedList<Class> classHierarchy;

    public ClassMap(DoubleLinkedList<Class> classHierarchy) {
        this.classHierarchy = classHierarchy;
    }


    public Class getSuperClassFrom(Class clazz) {

        // first find the class that is mentioned
        // return the next element in the chain
        if(classHierarchy.getFirst().getValue().equals(clazz)) {

            if(classHierarchy.getFirst().getNext() != null) {
                return classHierarchy.getFirst().getNext().getValue();
            }
            // if object does not have a superclass (which is very unlikely, there is always Object) return nothing
            else {
                return null;
            }
        }
        // class has no superclass, return null
        else if(classHierarchy.getLast().getValue().equals(clazz))
        {
            return null;
        }
        // search the double linked list starting at the top
        else {

            DoubleLinkedList<Class>.Node<Class> currentNode = classHierarchy.getFirst();
            Class result = null;
            while (currentNode != null) {

                if(currentNode.getValue().equals(clazz) && currentNode.getNext() != null) {
                   result = currentNode.getNext().getValue();
                   break;
                }

                currentNode = currentNode.getNext();

            }
            return result;
        }


    }
    public Class getSubClassFrom(Class clazz) {

      Class result = null;

      return result;
    }



}

