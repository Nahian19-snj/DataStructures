public interface List<E> {
    void add(E item); // Add to the end of the list

    void addFirst(E item); // Add to the front of the list

    E remove(E item);

    E get(int index);

    int size();

    boolean isEmpty();

    void clear();

    void printForward();

    void printBackward();


}
