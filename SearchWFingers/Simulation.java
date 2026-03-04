import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class Simulation {

    public static void main(String[] args) throws FileNotFoundException {
        
        DoubleLinkedList<Integer> linkedList = new DoubleLinkedList<>();

        Scanner scanner = new Scanner(new File("commands.csv"));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] words = line.split(",");

            switch (words[0]) {
                case "create":
                    linkedList = new DoubleLinkedList<>();
                    System.out.println("List created");
                    break;
                    
                case "insert":
                    // insert,position,item
                    if (words.length >= 3) {
                        int position = Integer.parseInt(words[1].trim());
                        Integer item = Integer.parseInt(words[2].trim());
                        boolean success = linkedList.insert(position, item);
                        if (success) {
                            System.out.println("Inserted " + item + " at position " + position);
                        } else {
                            System.out.println("Failed to insert " + item + " at position " + position);
                        }
                    }
                    break;
                    
                case "remove":
                    // remove,position
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
                    
                case "get":
                    // get,position
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
                    
                case "print":
                    // print,0 (unsorted) or print,1 (sorted)
                    if (words.length >= 2) {
                        int mode = Integer.parseInt(words[1].trim());
                        if (mode == 0) {
                            // Print in natural order
                            System.out.println("List (unsorted): " + linkedList.toString());
                        } else if (mode == 1) {
                            // Get array from list
                            Object[] arr = linkedList.toArray();
                            
                            // Sort the array with Comparator
                            java.util.Arrays.sort(arr, new Comparator<Object>() {
                                @Override
                                public int compare(Object x, Object y) {
                                    Integer a = (Integer) x;
                                    Integer b = (Integer) y;
                                    return a.compareTo(b); // Ascending order
                                }
                            });
                            
                            // Print sorted array
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
                        // Default: print unsorted
                        System.out.println("List: " + linkedList.toString());
                    }
                    break;
                    
                default:
                    System.out.println("Unknown Command: " + words[0]);
            }
        }
        
        scanner.close();
        System.out.println("\nFinal list: " + linkedList.toString());
    }
}