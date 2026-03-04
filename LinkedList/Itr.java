/*import java.util.Iterator;

public class Itr<T> implements Iterator<T> {
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
    
}*/
