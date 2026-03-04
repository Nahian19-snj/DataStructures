
public class Demo{

    public static void main(String[] args) {
     //  Queue<Integer> q = new LinkedList<>();

     ArrayQueue<Integer> q = new ArrayQueue<>(10);

   try {
     q.enqueue(10);
     q.enqueue(20);
      q.enqueue(30);
       q.enqueue(40);
        q.enqueue(50);
         q.enqueue(60);
          q.enqueue(70);
           q.enqueue(80);
          
    
   } catch (Exception e) {
    System.out.println(e);
   } 

   

    }
}