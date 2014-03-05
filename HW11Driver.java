
/**
 * This class will test all methods in the StingLinkedList class
 * 
 * @author Harrison Phung 
 * @version December 7, 2012
 */
public class HW11Driver{
    /**
     * Main method that will implement all methods in StringLinkedList class
     * 
     * @param = String array
     */
    public static void main(String[] args){
        StringLinkedList list = new StringLinkedList();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add(1, "Added Index");
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        System.out.println(list.get(3));
        System.out.println(list.size());
        System.out.println(list.indexOf("Added Index"));
        System.out.println(list.contains("Added Index"));
        System.out.println(list.contains("AFMASAS"));
        
        System.out.println(list.remove(1));
        System.out.println(list.size());
        System.out.println(list.get(0));
        System.out.println(list.get(1));
        System.out.println(list.get(2));
        
        list.clear();
        System.out.println(list.size());
        
    }
}
