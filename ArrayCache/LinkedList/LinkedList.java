import java.util.Iterator;

public class LinkedList<T> implements MyList<T>, Iterable<T>{
 Node<T> head;
    int size;

    public void MySinglyLinkedList() {

        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public void addFront(T data) {
        // create the node
        Node<T> newNode = new Node<>(data);
        // point the next to the head
        newNode.setNext(head);
        // point head to the new node
        head = newNode;
        size++;
        /*
         * head = tail = newNode;
         * newNode.setnext(head);
         * head.setprev(newNode);
         * 
         */
    }

    public void addLast_Bad(T data) {
        Node<T> newNode = new Node<>(data);

        if (isEmpty()) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
        size++;
    }
    @Override 
    public void addLast(T data){

    }

    @Override
    public void addAt(T data, T index) {

    }

    @Override
    public T removeFirst() {
        // chech if the list is empty
        if (isEmpty()) {
            return null;
        }
//create a temp to poin to the head 
        Node <T> temp = head;
        //set the head to point to null
        head = head.getNext();
        //decreased the size
        size--;
        return temp.getData();
    }

    @Override
    public <T> removeLast() {
         Node <T> temp = head;
         Node<T> newNode = new Node<>(e);
        if (isEmpty()){
            head = tail = newNode;
            
        }else 
        return head;
    }

    @Override
    public T removeAt(T index) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeAt'");
    }

public String toString(){
    String str = "The lis is:\n";
    if (isEmpty()) {
        return "List is Empty\n";
        
    }
    Node<T> cursor = head;
    while(cursor!= null){
        str+= cursor.getData() + "\n";
        cursor = cursor.getNext();
    }
    return str;
}

class Itr implements Iterator<T> {
  Node<T> cursor = head;
    @Override
    public boolean hasNext() {

        if (cursor== null) return false;

     return cursor.getNext() != null;

   }

    @Override
    public T next() {
  
        Node<T> temp = cursor;

        cursor = cursor.getNext();
        return temp.getData();
    }
    
}

@Override
public Iterator<T> iterator() {

     return new Iterator<>(){
         Node<T> cursor = head;

        @Override
        public boolean hasNext() {

        if (cursor== null) return false;

     return cursor.getNext() != null;

       }

        @Override
        public T next() {

        Node<T> temp = cursor;

        cursor = cursor.getNext();
        return temp.getData();
    }
       


     };
}
}

   

