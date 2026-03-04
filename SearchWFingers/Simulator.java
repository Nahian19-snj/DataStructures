import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
/**
 * The Simulation class reads commands from a CSV file 
 * and and executes methods to complete the commands.
 */

public class Simulator{

    /**
     * The main method runs the simulation by reading commands from a CSV file.
     * @param args  for 
     * @throws FileNotFoundException if the file is not found
     */
    public static void main(String[] args) throws FileNotFoundException {

        // Create an empty DoubleLinkedList of Integers
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        // Opens the file 
        Scanner scanner = new Scanner(new File("commands.csv"));

        // Process each line by command
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            
            // split by comma to extract command
            String[] words = line.split(","); 

           
            switch (words[0]) {

                // Command: create
                case "create":
                    linkedList = new DoubleLinkedList<>();
                    System.out.println("List created");
                    break;

                // Command: insert
                case "insert":
                    if (words.length >= 3) {
                        int position = Integer.parseInt(words[1].trim());
                        Integer item = Integer.parseInt(words[2].trim());

                        // Try inserting the item into the list
                        boolean add = linkedList.insert(position, item);

                        if (add) {
                            System.out.println("Inserted " + item + " at position " + position);
                        } else {
                            System.out.println("Failed to insert " + item + " at position " + position);
                        }
                    }
                    break;

                // Command: remove
                case "remove":
                    if (words.length >= 2) {
                        int position = Integer.parseInt(words[1].trim());
                        Integer removed = linkedList.remove(position);

                        if (removed != null) {
                            System.out.println("Removed " + removed + " from position " + position);
                        } else {
                            System.out.println("Failed to remove from position " + position);
                        }
                    }
                    break;

                // Command: get
                case "get":
                    if (words.length >= 2) {
                        int position = Integer.parseInt(words[1].trim());
                        try {
                            Integer item = linkedList.getEntry(position);
                            System.out.println("Item at position " + position + ": " + item);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Invalid position: " + position);
                        }
                    }
                    break;

                // Command: print
                
                case "print":
                    if (words.length >= 2) {
                        int sorting = Integer.parseInt(words[1].trim());

                        if (sorting == 0) {
                            // Print the list as it is 
                            System.out.println("List (unsorted): " + linkedList.toString());
                        } else if (sorting == 1) {
                            // Convert the list into an array
                            Object[] arr = linkedList.toArray();

                            // Sort the array using a Comparator
                            Arrays.sort(arr, new Comparator<Object>() {
                                @Override
                                public int compare(Object x, Object y) {
                                    Integer a = (Integer) x;
                                    Integer b = (Integer) y;
                                    return a.compareTo(b);
                                }
                            });

                            // Print the sorted list
                            System.out.print("List (sorted): ");
                            for (int i = 0; i < arr.length; i++) {
                                System.out.print(arr[i]);
                                if (i < arr.length - 1) {
                                    System.out.print(",");
                                }
                            }
                            System.out.println();
                        }
                    } else {
                        // If no mode provided, print unsorted by default
                        System.out.println("List: " + linkedList.toString());
                    }
                    break;

            }
        }

        // Close the file after processing
        scanner.close();

        // Display final list content at the end of simulation
        System.out.println("\nFinal list: " + linkedList.toString());
    }
}