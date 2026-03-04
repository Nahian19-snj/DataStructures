

public class Demo {

    /**
     * @param args
     */
    public static void main(String[] args) {

        Tree binaeryTree = new Tree();
      
          binaeryTree.insert(100);
            binaeryTree.insert(50);
             binaeryTree.insert(30);
              binaeryTree.insert(60);
                binaeryTree.insert(10);
                  binaeryTree.insert(40);
                    binaeryTree.insert(55);
                      binaeryTree.insert(80);
                        binaeryTree.insert(150);
                         binaeryTree.insert(140);
                          binaeryTree.insert(170);
                           binaeryTree.insert(160);
                            binaeryTree.insert(180);
                             binaeryTree.insert(130);
                         
//System.out.println("The binery tree inOrder is:");
//binaeryTree.inOrder();
 //System.out.println("The binery tree post order:");
//binaeryTree.postOrder();
//System.out.println("The binery tree Pre order:");
//binaeryTree.preOrder();
//System.out.println("The binery tree BFS:");
binaeryTree.BFS();

        
    }
    
}
