
    import java.util.*;


public class Test {
    public static void main(String[] args) {

 

        System.out.println("===== LIST TEST (ListADT) =====");

 

        List<String> list = new DoublyLinkedList<>();

 

        list.add("Alice");

        list.add("Bob");

        list.add("Charlie");

        list.add("Zara"); 

 

        System.out.println("Size after additions: " + list.size());

       

        System.out.println("List printed forward:");

        list.printForward();

 

        System.out.println("List printed backward:");

        list.printBackward();

 

        System.out.println("Removing Charlie...");

        list.remove("Charlie");

       

 

        System.out.println("Updated list forward:");

        list.printForward();

        System.out.println("Updated list backward:");

        list.printBackward();

 

        System.out.println("\n===== STACK TEST (Stack) =====");

 

        Stack<Integer> stack = new DoublyLinkedList<>();

        stack.push(10);

        stack.push(20);

        stack.push(30);

 

        System.out.println("Stack size: " + stack.size());

        System.out.println("Top using peek(): " + stack.peek());

        System.out.println("Popping...");

        System.out.println(stack.pop());

        System.out.println("Top after pop: " + stack.peek());

        System.out.println("Popping again...");

        System.out.println(stack.pop());

 

        System.out.println("Stack empty? " + stack.isEmpty());

 

        System.out.println("\n===== QUEUE TEST (Queue) =====");

 

        Queue<Double> queue = new DoublyLinkedList<>();

        queue.enqueue(1.5);

        queue.enqueue(2.5);

        queue.enqueue(3.5);

 

        System.out.println("Queue size: " + queue.size());

        System.out.println("Front using front(): " + queue.front());

 

        System.out.println("Dequeuing...");

        System.out.println(queue.dequeue());

        System.out.println("Front after dequeue: " + queue.front());

 

        System.out.println("Dequeuing again...");

        System.out.println(queue.dequeue());

        System.out.println("Queue empty? " + queue.isEmpty());

 

        System.out.println("\n===== CLEAR TEST =====");

        list.clear();

        stack.clear();

        queue.clear();

 

        System.out.println("List empty after clear? " + list.isEmpty());

        System.out.println("Stack empty after clear? " + stack.isEmpty());

        System.out.println("Queue empty after clear? " + queue.isEmpty());

    }

}
    

