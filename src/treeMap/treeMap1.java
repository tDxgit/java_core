package treeMap;
import java.util.TreeMap;
import java.util.Set;
import java.util.Iterator;
import java.util.Map;
/**
 * @author maychu
 */
public class treeMap1 {
   public static void main(String args[]) {
                // K, V
       TreeMap<Integer, Book> tmap = new TreeMap<Integer, Book>();          
      tmap.put( 1, new Book("Tutorial Java 1.1", "C1", 1990,1));
      tmap.put( 23, new Book("Tutorial Java 1.1", "C1", 1990,12));
      tmap.put( 70, new Book("Tutorial Java 1.1", "C1", 1990,12));
      tmap.put( 4, new Book("Tutorial Java 1.1", "C1", 1990,12));
      tmap.put( 2, new Book("Tutorial Java 1.1", "C1", 1990,12));   

      boolean kt = tmap.containsKey(23);// tim kiem nhi phan nhu tren cay NPTK
      if (kt) {System.out.println("The TreeMap containc key: 23");}
      else {System.out.println("The TreeMap not containc key: 23");}
      
      Book b=tmap.get(new Integer(4));
      if (b!=null ) System.out.println(b);
      else
          System.out.println("The TreeMap not containc key: 23");
      
      Iterator<Integer> iterator = tmap.keySet().iterator();
      while(iterator.hasNext()) {
         Integer key=iterator.next();
         System.out.print("key is: "+ key + " & Value is: ");
         System.out.println(tmap.get(key));
      }
   }
}