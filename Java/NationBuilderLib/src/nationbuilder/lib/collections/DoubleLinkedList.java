package nationbuilder.lib.collections;

/**
 * Created by patrick on 2/26/16.
 */

// TODO: bevat geen remove methode omdat deze generieke double linked list gemaakt is voor de ClassMap
public class DoubleLinkedList<T> {

    private Node<T> first;
    private Node<T> last;

    public Node<T> getLast() {
        return last;
    }
    public Node<T> getFirst() {
        return first;
    }



    public class Node<T> {
        private Node<T> previous;
        private Node<T> next;
        private T value;
        public Node(Node previous,Node next,T value) {
            this.previous  = previous;
            this.next = next;
            this.value = value;
        }

        public Node<T> getPrevious() {
            return previous;
        }
        public Node<T> getNext() {
            return next;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }
    }

    public void add(T value) {

        if(first != null) {

            this.add(value,first);
        }
        else {
            first = new Node<T>(null,null,value);
            last = first;
        }

    }
    private void add(T value,Node current) {

        if(current.next != null) {

            add(value,current.next);
        }
        else {
            current.next = new Node<>(current,null,value);
            last = current.next;
        }

    }

}
