public class DoubleLinkedList<T> implements ListInterface<T> {

    private DoubleNode<T> head;
    private DoubleNode<T> tail;
    private int itemCount;
    private Finger<T>[] fingers;

    /**
     * Default constructor
     */
    
    @SuppressWarnings("unchecked")
    public DoubleLinkedList() {
        head = null;
        tail = null;
        itemCount = 0;
        fingers = (Finger<T>[]) new Finger[0];
    }

    /**
     * Checks if the list is empty
     * @return 0
     */
    @Override
    public boolean isEmpty() {
        return (itemCount == 0);
    }

    /**
     * @return itemCount as length of the list
     */

    @Override
    public int getLength() {
        return itemCount;
    }


    /**
     * Inserts a new entry at a given position in the list.
     * @param position to insert at position 1
     * @param entry  the element to insert
     * @return true if insertion is successful otherwise returns false;
     */
    @Override
    public boolean insert(int position, T entry) {
        if (position < 1 || position > itemCount + 1)
            return false;

        DoubleNode<T> newNode = new DoubleNode<>(entry);

        //  inserts a element at beginning
        if (position == 1) {
            if (isEmpty()) {
                head = newNode;
                tail = newNode;
            } else {
                newNode.setNext(head);
                head.setPrev(newNode);
                head = newNode;
            }
        }
        // inserts at the end
        else if (position == itemCount + 1) {
            tail.setNext(newNode);
            newNode.setPrev(tail);
            tail = newNode;
        }
        //  inserts in the middle
        else {
            DoubleNode<T> current = getNodeAt(position);
            DoubleNode<T> prev = current.getPrev();

            newNode.setNext(current);
            newNode.setPrev(prev);
            prev.setNext(newNode);
            current.setPrev(newNode);
        }

        itemCount++;
        updateFinger();
        return true;
    }

    /**
     * Removes the node at the given position.
     * @param position to remove
     * @return the data of the removed node, or null if not found
     */
    @Override
    public T remove(int position) {

        if (position < 1 || position > itemCount || isEmpty())
            return null;

        T data;

        // Checks if it's only one node and removes it 
        if (itemCount == 1) {
            data = head.getItem();
            head = null;
            tail = null;
        }
        //  removes the head and sets the head to next node
        else if (position == 1) {
            data = head.getItem();
            head = head.getNext();
            head.setPrev(null);
        }
        // removes the  tail and sets the tail to the prev node 
        else if (position == itemCount) {
            data = tail.getItem();
            tail = tail.getPrev();
            tail.setNext(null);
        }
        // remove from middle and linked the before and after nodes together
        else {
            DoubleNode<T> temp = getNodeAt(position);
            data = temp.getItem();

            DoubleNode<T> nodePrev = temp.getPrev();
            DoubleNode<T> nodeNext = temp.getNext();

            nodePrev.setNext(nodeNext);
            nodeNext.setPrev(nodePrev);
        }

        itemCount--;
        updateFinger();
        return data;
    }

    /**
     * Clears the whole doubleLinkedList
     * set everything to null.
     */

    @Override
    public void clear() {
        head = null;
        tail = null;
        itemCount = 0;
    }

    /**
     * Returns the item at a specific position.
     *
     * @param position the 1-based position
     * @return the item stored at that position
     * @throws IndexOutOfBoundsException if the position is invalid
     */
    @Override
    public T getEntry(int position) throws IndexOutOfBoundsException {
        if (isEmpty() || position < 1 || position > itemCount)
            throw new IndexOutOfBoundsException();

        return getNodeAt(position).getItem();
    }

    /**
     * Replaces the element at a given position with a new one.
     *
     * @param position the position to replace
     * @param entry    the new element
     * @return the old element that was replaced
     * @throws IndexOutOfBoundsException if the position is invalid
     */
    @Override
    public T replace(int position, T entry) throws IndexOutOfBoundsException {
        if (isEmpty() || position < 1 || position > itemCount)
            throw new IndexOutOfBoundsException();

        DoubleNode<T> node = getNodeAt(position);
        T oldItem = node.getItem();
        node.setItem(entry);
        return oldItem;
    }

    /**
     * Converts the linked list into an array.
     *
     * @return an Object array containing all list elements in order
     */
    @Override
    public Object[] toArray() {
        if (itemCount == 0)
            return new Object[0];

        Object[] array = new Object[itemCount];
        DoubleNode<T> walker = head;

        for (int i = 0; i < itemCount; i++) {
            array[i] = walker.getItem();
            walker = walker.getNext();
        }
        return array;
    }
    /**
     * Returns the node at a specific position in the list.
     * @param position the 1-based position
     * @return the node at that position, or null if out of bound
     */

    private DoubleNode<T> getNodeAt(int position) {
        if (position < 1 || position > itemCount || isEmpty())
            return null;

        // Start search from the closest finger 
        Finger<T> closest = getClosest(position);
        DoubleNode<T> current = closest.getNode();
        int currentIdx = closest.getIndex();

        // Move forward or backward depending on where the target is
        if (currentIdx < position) {
            while (currentIdx < position) {
                current = current.getNext();
                currentIdx++;
            }
        } else if (currentIdx > position) {
            while (currentIdx > position) {
                current = current.getPrev();
                currentIdx--;
            }
        }

        return current;
    }


    /**
     * Returns the position of a specific entry in the list.
     * @param entry the element to search for
     * @return the index if found, otherwise returns -1 
     */
    public int getIndexOf(T entry) {
        if (isEmpty())
            return -1;

        DoubleNode<T> walker = head;
        int position = 1;

        while (walker != null) {
            if (walker.getItem().equals(entry))
                return position;

            position++;
            walker = walker.getNext();
        }
        return -1;
    }

    /**
     * Updates the finger array to optimize node access speed.
     * Calculates how many fingers do we need
     * places the finger so that we can get log(n)
     */

    @SuppressWarnings("unchecked")
    private void updateFinger() {
        if (itemCount == 0) {
            fingers = (Finger<T>[]) new Finger[0];
            return;
        }

        // Calculate how many fingers to use based on log2(size)
        int numFingers = (int) (Math.log(itemCount) / Math.log(2));

        // Ensure at least one finger
        if (numFingers < 1) numFingers = 1; 

        // Create new finger array
        fingers = (Finger<T>[]) new Finger[numFingers];

        // Determine spacing between fingers
        int spacing = itemCount / numFingers;
        if (spacing < 1) spacing = 1;

        // Place fingers evenly along the list
        int itr = 0;
        DoubleNode<T> cursor = head;
        int i = 1; // positions start at 1

        while (cursor != null && itr < numFingers) {
            if (i % spacing == 0) {
                fingers[itr] = new Finger<>(i, cursor);
                itr++;
            }
            cursor = cursor.getNext();
            i++;
        }
    }

    /**
     * Finds the closest finger to a given index.
     * @param idx the target position
     * @return the closest finger pointing near that position
     */

    private Finger<T> getClosest(int idx) {
        Finger<T> closest = new Finger<>(1, head);
        int minDistance = Math.abs(idx - 1);

        // Find the finger with the smallest distance to the index
        for (Finger<T> finger : fingers) {
            if (finger != null) {
                int distance = Math.abs(idx - finger.getIndex());
                if (distance < minDistance) {
                    minDistance = distance;
                    closest = new Finger<>(finger.getIndex(), finger.getNode());
                }
            }
        }
        return closest;
    }

    /**
     * Returns a string form.
     * @return the list elements as a string
     */
    @Override
    public String toString() {
        if (isEmpty())
            return null;

        String list = "";
        DoubleNode<T> walker = head;

        while (walker != null) {
            list += walker.getItem();
            if (walker.getNext() != null) {
                list += ",";
            }
            walker = walker.getNext();
        }

        return list;
    }
}
