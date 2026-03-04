import java.util.Iterator;

public class StackArray<E> implements StackInterface<E>, Iterable<E>{


  Object[] stack ;
  int size;
  int top;




  public StackArray(){
    size = 10;

  }

  public StackArray(int size){
    this.size = size;
    stack = new Object[size];
    top = -1;

  }




    @Override
    public void push(E e) throws StackException {
        if(isFull())
        throw new StackException("Stack is Full");

        stack[++top]=e;
   }

    @SuppressWarnings("unchecked")
    @Override
    public E pop() {
        if (isEmpty())
        return null;

    return (E)stack[top--];
        
   }

    @SuppressWarnings("unchecked")
    @Override
    public E peek() {

        if (isEmpty())
        return null;

    return (E) stack[top];
   }

    @Override
    public boolean isFull() {

        return top == size - 1;
   }

    @Override
    public boolean isEmpty() {
        return top  == -1;
   }

   public int getTop(){
    return top;
   }

   class Itr implements Iterator<E>{

    int cursor = top;

    @Override
    public boolean hasNext() {
        return cursor !=-1;

   }

    @SuppressWarnings("unchecked")
    @Override
    public E next() {
        return (E) stack[cursor--];
  }

   }

   @Override
   public Iterator<E> iterator() {

    return new Itr();
 }





}