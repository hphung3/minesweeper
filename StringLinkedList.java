/**
 * A Singly-Linked-List that can hold strings. It implements the StringList interface.
 * Fill in the methods according to the comments.
 * Don't forget to throw the proper exceptions in the methods that specify it.
 * 
 * @author - Harrison Phung
 * @version - December 7, 2012
 * I worked on this assignment alone.
 */
public class StringLinkedList implements StringList {
    
    /**
     * The head node of the linked list.
     */
    private StringNode head;
    private StringNode temp;
    /**
     * # of elements in list.
     */
    private int size;
    
    public StringLinkedList() {    
        //empty list.
        head = null;
        size = 0;
        temp = null;
    }
    
    @Override
    public void add(String string) {
        StringNode newnode = new StringNode(string);
        if (head == null){
            head = newnode;
            size++;
        }
        else{
            temp = head;
            while(temp.next != null){
               temp = temp.next;
            }
            temp.next = newnode;
            size++;
        }
    }
    
    @Override
    public void add(int index, String string) throws IndexOutOfBoundsException {
        StringNode newNode = new StringNode(string);
        int count = 0;
        temp = head;
        if (index == 0){
            if (temp == null){
                head = newNode;
                size++;
                return;
            }
            else{
                newNode.next = temp;
                head = newNode;
                size++;
                return;
            }
        }
        else{
            while (count != index-1 || index < 0 || index > size()){  
                if (temp == null)
                throw new IndexOutOfBoundsException();
                
                temp = temp.next;
                count++;
            }
           
            newNode.next = temp.next;
            temp.next = newNode;
            size++;
            return;
            }
    }
    
    @Override
    public String get(int index) {
        //return head.data;
        int count = 0;
        temp = head;
        while (count != index){   
            if (temp == null  || index < 0 || index > size()){
            throw new IndexOutOfBoundsException();
                }
            temp = temp.next;
            count++;
        }   
        return temp.data;
    }
    
    @Override
    public int size() {
        return size;
    }
    
    @Override
    public int indexOf(String e) {
        int count = 0;
        temp = head;
        while (temp != null && count < size()){
            if((temp.data).equals(e))
                return count;
            else{
                temp = temp.next;
                count++;
            }
            }
        return -1;
    }
    
    @Override
    public boolean contains(String e) {
        temp = head;
        while (temp != null){
            if((temp.data).equals(e))
                return true;
                else
                temp = temp.next;
            }
        return false;
    }
    
    @Override
    public String remove(int index) throws IndexOutOfBoundsException {
        int count = 0;
        temp = head;
        String returning;
        if (index < 0 || index > size() || head == null){
            throw new IndexOutOfBoundsException();
        }
        else{
            if (index == 0){
                returning = head.data;
                head = head.next;
                size--;
            }
            else{
                while (count != index-1){
                     temp = temp.next;
                     count ++;
                     }
                returning = temp.next.data;
                temp.next = temp.next.next;
                size--;
            }
        }
        if (returning == null)
        return null;
        return returning;
        
    }
    
    @Override
    public void clear() {
        head = null;
        size = 0;
    }
    
    /**
     * A StringLinkedList Node for you to fill in.
     * 
     * @author
     * @version
     * 
     */
    private class StringNode {
        private String data;
        private StringNode next;
        
        public StringNode(){}
        public StringNode(String s){
            data = s;
            next = null;
        }
        // Change this class any way you like
        
    }
}
