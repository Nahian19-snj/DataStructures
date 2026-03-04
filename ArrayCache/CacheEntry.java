/******************************************
 *  Author : Nahian Karim   
 *  Created On : Thu sep 25 2025
 *  File : CacheEntry.java
 *******************************************/

 public class CacheEntry implements CacheEntries{
    private String name;
    private String value;
    

    public CacheEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getValue() {
        return value;
       
    }

    @Override
    public void setName() {


   }

    @Override
    public void setValue() {
   
    }

    @Override
public String toString() {
    return "Name: " + name + " Value: " + value;
}

    
}
