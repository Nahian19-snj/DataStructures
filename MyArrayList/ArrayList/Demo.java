public class Demo {
    public static void main(String[] args) {
        MyArrayList<String> arrayList = new MyArrayList<>();

        try {
            arrayList.add("Nahian");
            arrayList.add("Karim");
            arrayList.add("Sanjana");
            System.out.println(arrayList);

            arrayList.remove();
            System.out.println("After removing last element: " + arrayList);

     // Add up to capacity

            for (int i = 0; i < 8; i++) {
         arrayList.add("Item" + i);
            }

   System.out.println("After adding more elements: " +arrayList);

 // This will throw an exception
     arrayList.add("OverflowItem");

  } catch (Exception e) {

 System.out.println("Exception caught: " + e.getMessage());

  }
    }


}

