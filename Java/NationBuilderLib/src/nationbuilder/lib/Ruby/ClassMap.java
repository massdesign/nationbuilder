package nationbuilder.lib.Ruby;

import java.util.ArrayList;
import java.util.Iterator;
import nationbuilder.lib.Ruby.Interfaces.RubyModel;
import nationbuilder.lib.collections.DoubleLinkedList;

import java.util.List;

/**
 * Created by patrick on 2/26/16.
 */
public class ClassMap implements Iterable<Class>,Iterator<Class>
{
    private DoubleLinkedList<Class> classHierarchy;

    private RubyModel currentObjectInstance;
    private boolean reverseIterator = false;
    DoubleLinkedList<Class>.Node<Class> currentNode;
    public ClassMap(DoubleLinkedList<Class> classHierarchy) {
        this.classHierarchy = classHierarchy;
    }

    public void reverseIterator() {
        this.reverseIterator = true;
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
    public Class getSubClassFromCurrent() {
        return this.getSubClassFrom(this.getCurrent());
    }
    public Class getSuperClassFromCurrent() {
        return this.getSuperClassFrom(this.getCurrent());
    }
    public Class getSubClassFrom(Class clazz) {

        // check of de eerste match met Class zodat we die case afgedekt hebben
        if(classHierarchy.getFirst().getValue().equals(clazz)) {

            return null;
        }
        //  de laatse checken of die match met de class en dan de previou teruggegeven
        else if(classHierarchy.getLast().getValue().equals(clazz)) {

            return classHierarchy.getLast().getPrevious().getValue();
        }
        else {

            // search the double linked list and begin at the bottom

            DoubleLinkedList<Class>.Node<Class> currentNode = classHierarchy.getLast();

            Class result = null;
            while (currentNode != null) {


                if(currentNode.getValue().equals(clazz) && currentNode.getPrevious() != null) {
                    result = currentNode.getPrevious().getValue();
                }

                currentNode  = currentNode.getPrevious();

            }
            return result;
        }
    }


    public RubyModel getCurrentObjectInstance()
    {
        return currentObjectInstance;
    }

    public void setCurrentObjectInstance(RubyModel currentObjectInstance)
    {
        this.currentObjectInstance = currentObjectInstance;
    }

    @Override
    public boolean hasNext()
    {
        boolean result = false;
        if(reverseIterator) {
            result  = currentNode == null && classHierarchy.getLast() != null;
            if(!result) {
                result = currentNode.getPrevious() != null;
            }

        }
        else {
            result = currentNode == null && classHierarchy.getFirst() != null;

            if(!result) {
                result = currentNode.getNext() != null;
            }
        }

        return  result;
    }

    @Override
    public Class next()
    {
        Class result = null;
        if(reverseIterator) {
            if(currentNode == null) {
                currentNode = classHierarchy.getLast();
            }
            else
            {
                currentNode = currentNode.getPrevious();
            }

        }
        else
        {
            if(currentNode == null) {
                currentNode = classHierarchy.getFirst();
            }
            else {
                currentNode = currentNode.getNext();
            }
        }
        result  = currentNode.getValue();

        return  result;
    }

    @Override
    public Iterator<Class> iterator()
    {
        return this;
    }

    public Class getCurrent() {
        return currentNode.getValue();
    }
}

