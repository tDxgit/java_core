package jdbc.arraylist;


public class SinhVien implements Comparable<SinhVien> {
	private String hoTen;
	private String ngaySinh;
	private String queQuan;
	private Float diemTB;

	SinhVien(){};

	SinhVien(String hoTen, String ngaySinh, String que, float diemTB){
		this.hoTen =hoTen;
		this.ngaySinh = ngaySinh;
		this.queQuan =que;
		this.diemTB = diemTB;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public float getDiemTB() {
		return diemTB;
	}

	public void setDiemTB(float diemTB) {
		this.diemTB = diemTB;
	};
    @Override
    public int compareTo(SinhVien a){
    	return this.diemTB.compareTo(a.diemTB);
    }
	@Override
	public String toString(){
		return "Ten: "+ this.hoTen+" Diem: "+this.diemTB.toString();
	}
}
