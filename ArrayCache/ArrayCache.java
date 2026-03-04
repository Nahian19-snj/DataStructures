/******************************************
 *  Author : Nahian Karim   
 *  Created On : Thu sep 25 2025
 *  File : ArrayCache.java
 *******************************************/


 public class ArrayCache {
    private CacheEntry[] entries;
    private int numEntries;
    private int numHits;
    private int numMisses;

    public ArrayCache(int sz){
         entries = new CacheEntry[sz];
        numEntries = 0;
        numHits =0;
        numMisses=0;
    

    }

    public ArrayCache(){
        entries= new CacheEntry[10];
        numEntries=0;
        numHits=0;
        numMisses=0;
    }

    /**
     * Shifts all cache entries one position to the right in the array.
     * This method moves elements starting from the second-to-last position
     * down to index 1 into the next higher index. The element at index 0 is
     * not moved. The last element in the array is overwritten.
     */

      private void shiftAll(){
  for (int i = entries.length -2; i > 0; i--) {
            entries[i+1] = entries[i];
        }
    }
    /**
     *Inserts a new cache entry at the front of the array.
     * Existing entries are shifted one position to the right to make room.
     * If the cache is full, the last entry is discarded.
     * @param name
     * @param value
     */

    public void put(String name, String value){
    
      shiftAll();

      entries[0]= new CacheEntry(name, value);
    if (numEntries< entries.length){
        numEntries++;
    }

    }

    /**
     * This method will shift entries from index 0 to endIdx-1 to the right by one.
     * @param endIdx
     */

    private void shiftEntries(int endIdx){
        for (int i = endIdx-1;i>=0;i--){
            entries[i+1]=entries[i];

        }
    }

    /**
     * get method for Search for an entry with the given name (linear search).
     * If found at index idx:
     * Save the found entry.
     * Shift all entries from index 0 to idx-1 to the right by one 
     * this will leave index 0 free and overwrite the found entry's current 
     * position with the entry from the left.
     * @param name
     * @return
     */

   public String get(String name) {

    // Linear search only up to numEntries
    for (int idx = 0; idx < numEntries; idx++) {

        if (entries[idx] != null && entries[idx].getName().equals(name)) {
            // Found entry
            CacheEntry found = entries[idx];

            // Shift [0 … idx-1] right by one
            shiftEntries(idx);

            // Place found entry at index 0
            entries[0] = found;

            // Count hit
            numHits++;

            return found.getValue();
        }
    }

    // Not found
    numMisses++;
    return null;
}

    public int getHits(){
        return numHits;
    }

    public int getMisses(){
        return numMisses;
    }

    public boolean isEmpty(){
        return numEntries==0;
    }

    public void clear(){
        numEntries=0;
        numHits=0;
        numMisses=0;
        for (int i =0; i< entries.length;i++){
            entries[i]=null;

        }
    }

     @Override
    public String toString() {
       if (isEmpty()){
        return "Cache is empty";

       }
       String cacheString ="";
       cacheString += "Entries: " + numEntries + "\n";
       cacheString += "Hits: " +numHits + "\n";
       cacheString += "Misses: " +numMisses + "\n";

       for (CacheEntries Entry: entries){
        if (Entry != null){
            cacheString += Entry +"\n";
        }

       }
           return cacheString;
    }
    

}