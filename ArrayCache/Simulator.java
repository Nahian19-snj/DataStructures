/******************************************
 *  Author : Nahian Karim   
 *  Created On : Thu sep 25 2025
 *  File : Simulator.java
 *******************************************/

 import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Simulator {

    /**  
     * simulate method to read queries, use ArrayCache, print cache after each line
     */
    public static void simulate(ArrayCache cache, String queryFileName) {

        try (Scanner sc = new Scanner(new File(queryFileName))) {


            while (sc.hasNextLine()) {


          String line = sc.nextLine().trim();

                if (line.isEmpty()) 
                continue;

                String[] parts = line.split(":");

                if (parts.length != 2) // to skip line 
                 continue; 

                  String url = parts[0];
                   String ip = parts[1];

                  // variable named TomJerry
                    String TomJerry = cache.get(url);

                   if (TomJerry == null) {
                    cache.put(url, ip);
                   }

                // Print the cache state after processing this line
                   System.out.println(cache.toString());
            
            }

          } catch (IOException e) {
            System.err.println("Error reading query file: " + queryFileName);
            e.printStackTrace();


        }
    }

    // printStats: show hit/miss rates with nice formatting
    public static void printStats(ArrayCache cache) {

        int hits = cache.getHits();
        int misses = cache.getMisses();
        int total = hits + misses;

        double hitRate = total > 0 ? (hits * 100.0) / total : 0.0;
        double missRate = total > 0 ? (misses * 100.0) / total : 0.0;

    
        System.out.printf("Hit Rate: %.2f%%\n", hitRate);
        System.out.printf("Miss Rate: %.2f%%%n", missRate);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Please enter query file name: ");
        String filename = input.nextLine();

        System.out.print("Please enter a positive cache size: ");
        int size = input.nextInt();

        ArrayCache cache = new ArrayCache(size);

        simulate(cache, filename);

        printStats(cache);


    }


}
