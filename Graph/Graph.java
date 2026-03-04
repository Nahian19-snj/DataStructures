import java.util.List;

/* *****************************************
 *  @Author : Ali Azhari   
 *  @Created On : Tue November 29 2025
 *  @File : Graph.java
 *  @Description: 
 *******************************************/

public interface Graph <V> {
    public int getSize();
    public List<V> getVertices();
    public V getVertex(int index);
    public int getIndex(V v);
    public List<Integer> getFriends(int index);
    public int getDegree(int v);
    public void printRelationships();
    public void clear();
    public boolean addVertex(V vertex);
    public boolean addRelationship(int u, int v);
    public boolean addRelationship(Relationship e);
    public boolean remove(V vertex);
    public boolean remove(int u, int v);
    public boolean remove(Relationship e);
    public FriendsGraph<V>.SearchTree dfs(V v);
    public FriendsGraph<V>.SearchTree bfs(V v);
   
}