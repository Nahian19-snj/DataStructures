public interface Queue <E> {
    void enqueue(E item);

    E dequeue();

    E front();

    int size();

    boolean isEmpty();

    void clear();
}
