import java.util.ArrayList;
import java.util.List;

/* *****************************************
 *  @Author : Nahian Karim Sanjana 
 *  @Created On : Sun December 14 2025
 *  @File : FriendsGraph.java
 *  @Description: The FriendsGraph class represents a directed graph of relationships between students.
 * Each vertex is a student, and each directed edge (u → v) indicates that student u
 * considers student v a friend. The graph supports adding and removing students,
 * adding and removing relationships, and performing depth-first search to identify
 * friendship groups.
 *******************************************/

public class FriendsGraph<V> implements Graph<V>{

    protected List<V> vertices = new ArrayList<>();
    protected List<List<Relationship>> friends = new ArrayList<>();


    public FriendsGraph() {
    }

    // Create vertices only using array of objects
    public FriendsGraph(V[] vertices) {
        for (int i = 0; i < vertices.length; i++) {
            addVertex(vertices[i]);
        }
    }

    // Create vertices only using list of objects
    public FriendsGraph(List<V> vertices) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }
       
    }

    // Create vertices and edges using array of objects and 2D array
    public FriendsGraph(V[] vertices, int[][] edges) {
        for (int i = 0; i < vertices.length; i++) {
            addVertex(vertices[i]);
        }
        createAdjacencyLists(edges, vertices.length);
    }

    // Create vertices and edges using list of objects and 2D array
    public FriendsGraph(List<V> vertices, int[][] edges) {
        for (int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }
        createAdjacencyLists(edges, vertices.size());
    }

   
    // Create vertices and edges using array of objects and list of edges
    private void createAdjacencyLists(int[][] relationships, int numberOfVertices) {
        for (int i = 0; i < relationships.length; i++) {
            addRelationship(relationships[i][0], relationships[i][1]);
        }
    }


    // get number of the graph
    @Override
    public int getSize() {
        return vertices.size();
    }

    // get all vertices
    @Override
    public List<V> getVertices() {
        return vertices;
    }

    // Create vertices and edges using list of objects and list of edges
    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    // get index of a vertex
    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    // get friends of a student using the index of the student
    @Override
    public List<Integer> getFriends(int index) {
        List<Integer> result = new ArrayList<>();
        for (Relationship e : friends.get(index)) {
            result.add(e.v);
        }
        return result;
    }

    // get degree of a vertex
    @Override
    public int getDegree(int v) {
        return friends.get(v).size();
    }

    // print the relationships
    @Override
    public void printRelationships() {
        for (int u = 0; u < friends.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for (Relationship e : friends.get(u)) {
                System.out.print("(" + getVertex(e.u) + ", " + getVertex(e.v) + ") ");
            }
            System.out.println();
        }
    }

    // print the graph
    @Override
    public void clear() {
        vertices.clear();
        friends.clear();
    }

    // add a vertex
    @Override
    public boolean addVertex(V vertex) {
        if (!vertices.contains(vertex)) {
            vertices.add(vertex);
            friends.add(new ArrayList<Relationship>());
            return true;
        } else {
            return false;
        }
     }


    // add a relationship
    @Override
    public boolean addRelationship(Relationship e) {
        if (e.u < 0 || e.u > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.u);
        }
        if (e.v < 0 || e.v > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.v);
        }
        if (!friends.get(e.u).contains(e)) {
            friends.get(e.u).add(e);
            return true;
        } else {
            return false;
        }
    }

    // add a relationship
    @Override
    public boolean addRelationship(int u, int v) {

         return addRelationship(new Relationship(u, v));

    }

    // Depth First Search
    public SearchTree dfs(V v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        boolean[] isVisited = new boolean[vertices.size()];
        dfs(getIndex(v), parent, searchOrder, isVisited);
        return new SearchTree(getIndex(v), parent, searchOrder);
    }

    // Depth First Search
    private void dfs(int v, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(v);
        isVisited[v] = true;
        for (Relationship e : friends.get(v)) {
            if (!isVisited[e.v]) {
                parent[e.v] = v;
                dfs(e.v, parent, searchOrder, isVisited);
            }
        }
    }

    // Breadth First Search
    public SearchTree bfs(V v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for (int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        java.util.LinkedList<Integer> queue = new java.util.LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(getIndex(v));
        isVisited[getIndex(v)] = true;
        while (!queue.isEmpty()) {
            int u = queue.poll();
            searchOrder.add(u);
            for (Relationship e : friends.get(u)) {
                if (!isVisited[e.v]) {
                    queue.offer(e.v);
                    parent[e.v] = u;
                    isVisited[e.v] = true;
                }
            }
        }
        return new SearchTree(getIndex(v), parent, searchOrder);
    }

    // Search Tree
    public class SearchTree {
        private int root;
        private int[] parent;
        private List<Integer> searchOrder;

        public SearchTree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int v) {
            return parent[v];
        }

        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            } while (index != -1);
            return path;
        }

        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path from " + vertices.get(root) + " to " + vertices.get(index) + ": ");
            for (int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + " ");
            }
        }

        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for (int i = 0; i < parent.length; i++) {
                if (parent[i] != -1) {
                    System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }
    

    // Remove a vertex (TODO: Implement this method)
    @Override
    public boolean remove(V vertex) {
        int index = getIndex(vertex);


       for(int i = 0; i<friends.size();i++){

        List<Relationship> list = friends.get(i);
        for (int j = list.size()-1;j>=0;j--){
            Relationship relationship = list.get(j);
            if(relationship.v==index){
                list.remove(j);
            }
        }
       }
       friends.remove(index);
       vertices.remove(index);

     for (int i = 0; i < friends.size(); i++) {
        List<Relationship> list = friends.get(i);
        for (Relationship r : list) {
            // Any endpoint index greater than the removed index needs to be decremented
            if (r.u > index) {
                r.u--;
            }
            if (r.v > index) {
                r.v--;
            }
        }
    }

    return true;
     
    }

    // Remove a vertex (TODO: Implement this method)
    @Override
    public boolean remove(int u, int v) {

 if (u < 0 || u >= getSize())
     return false;
    
    if (v < 0 || v >= getSize()) 
        return false;

    List<Relationship> adj = friends.get(u);

    for (int i = 0; i < adj.size(); i++) {
        Relationship e = adj.get(i);
        if (e.u == u && e.v == v) {  // match this directed edge
            adj.remove(i);
            return true;
        }
    }
    return false;  // not found
    }

    // Remove a relationship (TODO: Implement this method)
    @Override
    public boolean remove(Relationship e) {
     if (e == null)
         return false;
    return remove(e.u, e.v);
    }

}