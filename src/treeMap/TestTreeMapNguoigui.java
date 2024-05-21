package treeMap;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 *
 * @author maychu
 */
public class TestTreeMapNguoigui {

    public static void main(String args[]) throws ParseException {
        SortedMap<NguoiGui, Double> tmap = new TreeMap<NguoiGui, Double>();
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
       // Date d=df.parse("20/10/1976");
        // NguoiGui ng= new NguoiGui("Nguyen Van A", "Ha Noi", new Date("1/1/1980"));        
        tmap.put(new NguoiGui("A", "Nguyen Van A", "Ha Noi D", df.parse("20/10/1998")), new Double(0.0f));
        tmap.put(new NguoiGui("B", "Nguyen Van B", "Ha Noi C", df.parse("22/10/1979")), new Double(0.0f));
        tmap.put(new NguoiGui("C", "Nguyen Van C", "Ha Noi A", df.parse("23/10/1980")), new Double(0.0f));
        tmap.put(new NguoiGui("D", "Nguyen Van D", "Ha Noi B", df.parse("24/10/1987")), new Double(0.0f));
        
       System.out.println("Sorted by Mã:" + Arrays.toString(tmap.entrySet().toArray()));
        // Gui tien
        NguoiGui ng = new NguoiGui("A", "Nguyen Van A", "Ha Noi", df.parse("20/10/1978"));
        double balance = tmap.get(ng);
        tmap.put(ng, new Double(balance + 1000));
        System.out.println("Balance hien tai cua: " + ng.Hoten + " la: " + tmap.get(ng));

        SortedMap<NguoiGui, Double> tmap1 = new TreeMap<NguoiGui, Double> 
        (new Comparator<NguoiGui>() {
            public int compare(NguoiGui a, NguoiGui b) {
                return a.ngaysinh.compareTo(b.ngaysinh);
            }
        });
        tmap1.putAll(tmap);
        System.out.println("Sorted Map theo ngày sinh: " + Arrays.toString(tmap1.entrySet().toArray()));

        //Sap xep giam dan theo so tien trong tai khoan,
        // Neu so tien trong tai khoan bang nhau thi sx theo NguoiGui
        SortedMap<NguoiGui, Double> tmap2
                = new TreeMap<NguoiGui, Double>(new ValueComparator(tmap));
        tmap2.putAll(tmap);
        System.out.println("Sorted Map by ti�?n gửi: " 
                + Arrays.toString(tmap2.entrySet().toArray()));
        
    }

}
