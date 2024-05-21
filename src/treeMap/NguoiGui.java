package treeMap;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class NguoiGui implements Comparable<NguoiGui>{
    public String ma;//CMT
    public String Hoten;
    public String Diachi;
    public Date  ngaysinh;   
    public NguoiGui(String m, String ht, String dc, Date ns){
        this.ma=m;
        Hoten=ht; Diachi=dc; ngaysinh=ns;        
    }
     @Override
    public int compareTo(NguoiGui o) {
        return ma.compareTo(o.ma);
    }
    @Override
    public String toString(){
          DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return "(Ma: "+ ma +", Hoten: "+Hoten+", Dia chi: "+Diachi+", Ngay sinh: "+ df.format(ngaysinh).toString()+")";
    }
    
}
