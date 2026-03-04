public interface QueueInterface <E>{

    


    public void enqueue(E e) throws QueueException ;
    public E dequeue();
    public boolean offer();
    public E peek();
    public boolean isEmpty();
    public boolean isFull();


  
     




    
}
