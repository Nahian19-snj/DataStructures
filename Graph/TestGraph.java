import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/* *****************************************
 *  @Author : Nahhian Karim sanjana
 *  @Created On : Tue November 29 2025
 *  @File : TestGraph.java
 *  @Description: This testGraph test the graph methods 
 *******************************************/
public class TestGraph {


    /*
     * Method to read the student names from the file
     */
    public static ArrayList<String> readData() throws FileNotFoundException {

        ArrayList<String> students = new ArrayList<>();
       try (Scanner scanner = new Scanner(new File("Students.txt"))) {

            while (scanner.hasNextLine()) {
                String name = scanner.nextLine();
                students.add(name);
            }
    }
        return students;
    }

    /*
     * Method to read the relationships from the file
     */
    public static int[][] readRelationships() throws FileNotFoundException {

        List<int[]> rows = new ArrayList<>();

       try (Scanner scanner = new Scanner(new File("Relationships.txt"))) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] numStrings = line.trim().split("\\s+");
                // Create an array of integers for this row
                int[] row = new int[numStrings.length];
                for (int i = 0; i < numStrings.length; i++) {
                    row[i] = Integer.parseInt(numStrings[i]);
            }
                rows.add(row);
            }
        }
            // Convert List of int[] to int[][]
        int[][] edges = new int[rows.size()][];
        for (int i = 0; i < rows.size(); i++) {
            edges[i] = rows.get(i);
        }
   
        return edges;
        
    }

    /*
     * Method to shuffle the student names
     */
    public static void shuffler(ArrayList<String> students) {
        for (int i = 0; i < students.size(); i++) {
            int randomIndex = (int) (Math.random() * students.size());
            String temp = students.get(i);
            students.set(i, students.get(randomIndex));
            students.set(randomIndex, temp);
        }

    }

    /*
     *  Method to get the student name from the user
     */
    public static String getStudentName() {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter your name: ");
            String name = scanner.nextLine();
            return name;
        }
    }

    /*
     * Method to print the student names
     */
    public static void printStudents(ArrayList<String> students) {
        System.out.print("[");
        for (int i = 0; i < students.size(); i++) {
            
            System.out.print(students.get(i));
            if (i < students.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    /*
     * Method to print the graph using DFS
     */
    public static void printGraphDFS(FriendsGraph<String> graph) {
        FriendsGraph<String>.SearchTree dfs;
        for (int i = 0; i < graph.vertices.size(); i++) {
           
            dfs = graph.dfs(graph.vertices.get(i));
            if (dfs.getNumberOfVerticesFound() == 1) {
                System.out.println("\n" + graph.vertices.get(i) + " is a friendless student");
                continue;
            }
            System.out.println("\n" +graph.vertices.get(i) + " with index " + i + "  is in a group of  " + dfs.getNumberOfVerticesFound() + " Friends");
            java.util.List<Integer> searchOrders = dfs.getSearchOrder();
            for (int j = 0; j < searchOrders.size(); j++) {
                System.out.print(graph.getVertex(searchOrders.get(j)));
                if (j < searchOrders.size() - 1) {
                    System.out.print(" -> ");
                }
            }
        }
            System.out.println();
    }
    
    public static void main(String[] args) throws FileNotFoundException {

        ArrayList<String> students = new ArrayList<>();
        try {
            students = readData();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("\nThe names read from file are: " );
        printStudents(students);

        shuffler(students);
        System.out.println("\nThe shuffled names are ");
        printStudents(students);

        int[][] relationships = readRelationships();
        
         FriendsGraph<String> graph = new FriendsGraph<>(students);
         
        for (int i = 0; i < relationships.length; i++) {
           graph.addRelationship(new Relationship(relationships[i][0], relationships[i][1]));
        }
        System.out.println("\nThe graph was created with " + graph.getSize() + " students and " + relationships.length + " relationships.");


        System.out.println("The relationship for each student is: ");
       
      
       printGraphDFS(graph);


            System.out.println("Enter a student name to remove from the graph: ");
            String name = getStudentName();
            if (graph.remove(name)) 
                System.out.println(name + " was removed from the graph.");
            else 
                System.out.println(name + " is not in the graph.");
            
                printGraphDFS(graph);
        
}

}
