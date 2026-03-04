/* *****************************************
 *  @Author : Nahian Karim Sanjana
 *  @Created On : Sun Decembeer 14 2025
 *  @File : Relationship.java
 *  @Description: The Relationship class represents a directed edge in the FriendsGraph.
 * Each Relationship stores a source vertex index (u) and a destination
 * vertex index (v), meaning there is a friendship from u to v.
 *******************************************/

public class Relationship {
    
    public int u;
    public int v;
    
    public Relationship(int u, int v) {
        this.u = u;
        this.v = v;
    }
    
    public boolean equals(Object o) {
        return u == ((Relationship)o).u && v == ((Relationship)o).v;
    }
    
    public String toString() {
        return "(" + u + " -> " + v + ")";
    }
    
    public int hashCode() {
        return toString().hashCode();
    }
    
    public int compareTo(Relationship e) {
          // First it's compare the source vertex
            if (this.u != e.u) {
            return Integer.compare(this.u, e.u);
        }
        // If sources are equal, compare the destination
        return Integer.compare(this.v, e.v);
     
    }

    
    
    public boolean contains(int v) {
        return u == v || this.v == v;
    }
    
    public boolean contains(Relationship e) {
        return contains(e.u) && contains(e.v);
    }
    
    public boolean isSelfLoop() {
        return u == v;
    }
    
    public boolean isAdjacent(Relationship e) {
        return u == e.u || u == e.v || v == e.u || v == e.v;
    }
    
    public boolean isAdjacent(int vertex) {
        return u == vertex || v == vertex;
    }
    
    public int other(int vertex) {
        if (vertex == u) {
            return v;
        } else if (vertex == v) {
            return u;
        } else {
            throw new IllegalArgumentException("Illegal endpoint");
        }
    }
}
