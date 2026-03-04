
public class Tree{

    

       Node parent;
        Node temp;
        Node root;



     
   public void insert(int value) {
    Node current = new Node(value);
    if (root == null) {
        root = current;
        return;
    }

    Node parent = null;
    Node temp = root;

    while (temp != null) {
        parent = temp;

        if (current.getVal() <= temp.getVal()) {
            temp = temp.left;
        } else {
            temp = temp.right;
        }
    }

    if (current.getVal() <= parent.getVal()) {
        parent.left = current ;
    } else {
        parent.right = current;
    }
}

/**
 * InOrder: the 2nd time you see the node 
 * preOrder:the 1st time you see the node and mark the node 
 * post order: the last time you see the node and make it marked
 * BFS : breadth first search node -> 
 * DFS : depth first search -> 
 */

    public void inOrder(){
        inOrder(root);
    }
        public void inOrder(Node root){
        if(root != null){
            inOrder(root.left);
            System.out.println(root.getVal());
            inOrder(root.right);
        }
        
    }
    public void preOrder(Node root){
        if(root!= null){

        System.out.println(root.getVal());
        preOrder(root.left);
        preOrder(root.right);
    }
}
public void preOrder(){
    preOrder(root);
}

public void postOrder(Node root){
    if(root!= null){
        postOrder(root.left);
        postOrder(root.right);
        System.out.println(root.getVal());
    }
}
public void postOrder(){
    postOrder(root);
}
public void BFS(Node root){
   if(root != null ){
    System.out.println(root.getVal());
    BFS(root.left);
    BFS(root.right);

   }

    }

    public void BFS(){
        BFS(root);
    }

}

      
 

