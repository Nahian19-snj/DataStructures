public class Demo {

    public static void main(String[] args) {
        
        StackArray<Integer> s = new StackArray<>(5);
     
        try {
            s.push(10);
            s.push(20);
            s.push(30);
            s.push(40);
            s.push (50);
            s.push(60);
        } catch (StackException e) {
         System.out.println(e.getMessage());
        }

        Integer element =s.pop();
        System.out.println("the element poped is"+ element);

       /*  while(!s.isEmpty()){
            System.out.println("the element popped is "+s.pop());
        }

        int top = s.getTop();
        while(top>-1){

        }*/

        for(Integer e : s ){
            System.out.println(e);
        }
    

    }
    
}
