public interface MyList<E> {
    public void add( E elements);
    public E removeLast();
    public E removeFirst();
    public E remove(int index);
    public boolean isEmpty();
    public boolean isFull();
}
