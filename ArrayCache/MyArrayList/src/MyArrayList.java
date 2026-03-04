import java.util.Arrays;
import java.util.Iterator;


public class MyArrayList<E> implements MyList<E>, Iterable<E> {
    int capacity;
    Object elements[];
    int size;

    // default
    public MyArrayList() {
        this(10);

    }

    public MyArrayList(int capacity) {
        this.capacity = capacity;
        size = 0;
        elements = new Object[capacity];

    }


    @Override
    public boolean isFull() {
        if (size == capacity)
            return true;
        else return false;
    }


    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        else return false;
    }


    @Override
    public void add(E element) {
        if (isFull()) {
            grow();
        }
        elements[size++] = element;


    }

    private void grow() {
        capacity *= 2;
        Object temp[] = new Object[capacity];

        for (int i = 0; i < size; i++) {
            temp[i] = elements[i];

            elements = temp;
        }
    }

    @Override
    public E removeLast() {
        if (isEmpty())
            return null;
        else return (E) elements[size--];
    }

    @Override
    public E removeFirst() {
        return remove(0);
    }

    @Override
    public E remove(int index) {
        if (isEmpty())
            return null;
        @SuppressWarnings("unchecked") E e = (E) elements[index];
        for (int i = index; i < size; i++) {
            elements[i - 1] = elements[i];

        }
        size--;
        return e;
    }


    @Override
    public Iterator<E> iterator() {

        return new Iterator<>() {
            int cursor = 0;
            @Override
            public boolean hasNext() {
                return cursor != size;
            }

            @Override
            public E next() {
                return (E) elements[cursor++];
            }
        };
    }
}



