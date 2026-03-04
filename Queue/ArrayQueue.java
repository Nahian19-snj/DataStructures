public class ArrayQueue<E> implements QueueInterface<E> {

E[] data;
int front;
int rear;
int size;
int capacity;

  @SuppressWarnings("unchecked")
public ArrayQueue(int capacity){
    this.capacity = capacity;
    data = (E[])new Object[capacity];
    front = 0;
    rear = -1;

  }

    @Override
    public void enqueue(E e) throws QueueException {
        if (isFull()) 
           throw new QueueException("Queue is Full");

           data [++rear]= e;
           size++;

   }

    private void shiftQueue(){

        for(int i=0; i<size-1; i++){
            data[i]= data[i+1];
        }
    }

    @Override
    public E dequeue() {

        if(isEmpty())
          return null;

          E temp = data[front];
    
          shiftQueue();

          size--;
          rear--;

          return temp;



   }

    @Override
    public boolean offer() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'offer'");
    }

    @Override
    public E peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

    @Override
    public boolean isEmpty() {

        return size == 0;
    }

    @Override
    public boolean isFull() {

        return size == capacity;
   }

   public int size(){
    return size;
   }
    
}
// natual order is 
// if it's lots of remove sorted best 
// but if insert then unsorted 
// parent =(p-1)/2
// children we don;t need that cus up heep 
// child 2p+1 = left ; 2p+2 = right 
//
