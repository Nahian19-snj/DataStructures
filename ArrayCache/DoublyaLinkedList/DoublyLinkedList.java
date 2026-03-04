public class DoublyLinkedList<E> implements List<E>, Stack<E>, Queue<E> {

    private Node<E> head;
    private Node<E> tail;
    private Node<E> current;
    private int size;

 
  

    @Override
    public void add(E item) {
        Node<E> newNode = new Node<E>(item);
        if(isEmpty()){
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;

  }

    @Override
    public void addFirst(E item) {
          Node<E> newNode = new Node<E>(item);

           if(isEmpty()){
             head = tail = newNode;
              }
              else {
                head.prev = newNode;
                newNode.next = head;
                head = newNode;
              }
              size++;
    }

    @Override
    public E remove(E item) {

        Node<E> current = new Node<E>(item);
        Node<E> curPrev = current.prev;
        Node<E> curNext = current.next;
        if(current.data.equals(item)){
            if(current == head){
                 head = null;

            } else if(current == tail){
                tail = null;

            } else{
                curPrev.next = curNext;
                curNext.prev = curPrev;
                current = null;
   }
          
        }

     
  return current.data;


   }

    @Override
    public E get(int index) {
        Node<E> current = head;
        while(current!= null){
            for (int i =0; i<index; i++){
                current = current.next;
            }
        }
        return (E) current;

   }

    @Override
    public int size() {
       return size;
  }

    @Override
    public boolean isEmpty() {
        return size == 0;
   }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
  }

    @Override
    public void printForward() {
        Node<E> cuNode = head;
            for (int i =0; i<size; i++){
                System.out.println(cuNode.data);
                cuNode = cuNode.next;
            }
    }

    @Override
    public void printBackward() {
        Node<E> cuNode = tail;
        for(int i=0; i<size-1; i--){
            System.out.println(cuNode.data);
            cuNode = cuNode.prev;
        }
   }

      @Override
    public void push(E item) {
            add(item);
   }

    @Override
    public E pop() {
    
        if(isEmpty()){
            return null;
        }
        else{
            tail = tail.prev;
            tail = null;
        
        }

        return tail.data;
  
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            return null;        
        }
        else {
            return tail.data;
        }

  }

   @Override
    public void enqueue(E item) {
        addFirst(item);

}

    @Override
    public E dequeue() {
        if(isEmpty()){
            return null;

        }else{
            head = head.next;
            head = null;

        }
        return head.data;
    }

    @Override
    public E front() {
        if(isEmpty()){
            return null;
        } else{
          return head.data;
        }
    }

    
}
