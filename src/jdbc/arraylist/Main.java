package jdbc.arraylist;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
	public static void main(String[] args) {
        ArrayList<SinhVien> arrList = new ArrayList<SinhVien>();
        Connect kn = new Connect("jdbc:mysql://localhost/qlsinhvien","root","root");
        
        arrList = kn.ResultArrList("select * from sinhvien");
        System.out.println("Ban dau: "+arrList);

        // them sinh vien
        SinhVien sv = new SinhVien("Ta Ngoc Duy","12/12/1994","Lang Son",1.0F);
        //them vao csdl
        kn.addSinhVien(sv);
        
        arrList = kn.ResultArrList("select * from sinhvien");
        System.out.println("Sau them: "+ arrList);

        // xoa vao csdl
        String name = "Ta Ngoc Duy";
        kn.deleteSV(name);
        arrList = kn.ResultArrList("select * from sinhvien");
        System.out.println("Sau xoa: "+ arrList);

        // tim theo diem trong arrList
        Collections.sort(arrList);
        System.out.println("Mang sau khi sap xep: "+ arrList);
        SinhVien sv2 = new SinhVien("  ","  ","  ",7.8F);
        int i = Collections.binarySearch(arrList,sv2);
        if(Collections.binarySearch(arrList,sv2)!=-1){
        	System.out.println("Ket qua tim kiem voi diem: 7.8 "+" la : "+ arrList.get(i));
        }
        // tim kiem theo ten arrList
        SinhVien sv3 = new SinhVien("Tran Van C","  ","  ",7.8F);
        int j = Collections.binarySearch(arrList,sv3,new Comparator<SinhVien>() {
        	@Override
        	public int compare(SinhVien a, SinhVien b){
        		return a.getHoTen().compareTo(b.getHoTen());
        	}
		});

        if(j!=1){
        	System.out.println("Ket qua tim kiem voi ten: Tran Van C la : "+ arrList.get(i));
        }

        // sua sinh vien co ten Tran van C 0.0
        SinhVien sv4 = new SinhVien("Tran Van B","4/5/1993","Ha Tinh",0.0F);
        if(kn.suaSV(sv4)== true ){
        	System.out.println("sua thanh cong");
        };
        arrList = kn.ResultArrList("select * from sinhvien");
        System.out.println("Ket qua sau khi sua sinh vien B:  "+ arrList);
	}

}
