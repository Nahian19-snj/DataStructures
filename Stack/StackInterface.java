public interface StackInterface<E> {
    

   public  void push(E e) throws StackException;
   public E pop();
   public E peek();
   public boolean isFull();
   public boolean isEmpty();



}
