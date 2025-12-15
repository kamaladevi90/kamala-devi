package datastructures;

public class SinglyLinkedList<T> {

    private Node<T> head;

    public void add(T data) {
        Node<T> newNode = new Node<>(data, null);
        if (head == null) {
            head = newNode;
            return;
        }

        Node<T> current = head;
        while (current.next() != null) {
            current = current.next();
        }
        current.setNext(newNode);
    }

    public boolean remove(T data) {
        if (head == null) return false;

        if (head.data().equals(data)) {
            head = head.next();
            return true;
        }

        Node<T> current = head;
        while (current.next() != null && !current.next().data().equals(data)) {
            current = current.next();
        }

        if (current.next() == null) return false;

        current.setNext(current.next().next());
        return true;
    }

    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data() + " -> ");
            current = current.next();
        }
        System.out.println("null");
    }

    // Node using Java 17 record (immutable fields, but mutable reference for next)
    public static final class Node<T> {
        private final T data;
        private Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }

        public T data() {
            return data;
        }

        public Node<T> next() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }
    
    
    public void insertSorted(T data) {
        Node<T> newNode = new Node<>(data, null);

        if (head == null || compare(data, head.data()) < 0) {
            newNode.setNext(head);
            head = newNode;
            return;
        }

        Node<T> current = head;
        while (current.next() != null && compare(current.next().data(), data) < 0) {
            current = current.next();
        }

        newNode.setNext(current.next());
        current.setNext(newNode);
    }

    @SuppressWarnings("unchecked")
    private int compare(T a, T b) {
        return ((Comparable<T>) a).compareTo(b);
    }

    // Sample usage
    public static void main(String[] args) {
        SinglyLinkedList<String> list = new SinglyLinkedList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.printList(); // A -> B -> C -> null

        list.remove("B");
        list.printList(); // A -> C -> null
        
        
        
        
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>();
        list1.insertSorted(10);
        list1.insertSorted(5);
        list1.insertSorted(15);
        list1.insertSorted(12);
        list1.printList();  // 5 -> 10 -> 12 -> 15 -> null

    }
}
