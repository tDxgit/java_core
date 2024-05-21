package treeMap;
import java.util.Comparator;
import java.util.Map;
public class ValueComparator implements Comparator<NguoiGui> {
    private Map<NguoiGui, Double> map;    
    public ValueComparator(Map<NguoiGui, Double>  map) {
        this.map = map;
    }
    public int compare(NguoiGui a, NguoiGui b) {         
        //map.get(b) -- tien gui cua nguoi b
        //map.get(a) -- -- tien gui cua nguoi a
        int i= map.get(b).compareTo(map.get(a)); // giam dan theo tien gui
       // (a, b) =>tang dan; (b, a=> giam dan) theo so tien trong tai khoan
        if (i==0){// neu so tien gui bang nhau
            return a.compareTo(b);//
        }
        return i;
    }
}