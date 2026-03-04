public interface MyList <T>{

      public void addFront(T data);

    public void addLast(T data);

    public void addAt(T data, T index);

    public T removeFirst();

    public T removeLast();

    public T removeAt(T index);

    public boolean isEmpty();

}

